package com.jhg.marketing.web.controller;

import com.alibaba.fastjson.JSON;
import com.jhg.marketing.dao.domin.SysUser;
import com.jhg.marketing.dao.domin.WeChatPay;
import com.jhg.marketing.dao.domin.WeChatPayRefund;
import com.jhg.marketing.dao.domin.WeChatPayRefundApply;
import com.jhg.marketing.dao.mapper.WeChatPayMapper;
import com.jhg.marketing.dao.mapper.WeChatPayRefundApplyMapper;
import com.jhg.marketing.dao.mapper.WeChatPayRefundMapper;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.ApplicationUtil;
import com.jhg.marketing.web.util.DateUtil;
import com.jhg.marketing.web.util.StringUtil;
import com.jhg.marketing.web.util.ip.IpUtil;
import com.jhg.marketing.web.util.previewRegister.PreviewRegisterUtil;
import com.jhg.marketing.web.util.weChatPay.WXPay;
import com.jhg.marketing.web.util.weChatPay.WXPayConfigImpl;
import com.jhg.marketing.web.util.weChatPay.WXPayConstants;
import com.jhg.marketing.web.util.weChatPay.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付
 *
 * @author lxl
 */
@Slf4j
@Controller
@RequestMapping("/weChatPay")
public class WeChatPayController {

    @Value("${user.domain}")
    private String domain;
    @Value("${user.wechat.useSandbox}")
    private String useSandbox;
    @Autowired
    private WXPayConfigImpl config;
    @Autowired
    private WeChatPayMapper weChatPayMapper;
    @Autowired
    private WeChatPayRefundMapper weChatPayRefundMapper;
    @Autowired
    private WeChatPayRefundApplyMapper weChatPayRefundApplyMapper;

    /**
     * 统一下单
     *
     * @param request
     * @param description 商品描述
     * @param totalFee
     * @return
     */
    @ResponseBody
    @RequestMapping("unifiedOrder")
    public Message unifiedOrder(HttpServletRequest request, String description,
                                int totalFee, @RequestParam("data") String paramData) {
        String openId = ApplicationUtil.getSessionOpenId();
        boolean useSandbox = Boolean.parseBoolean(this.useSandbox);
        WXPay wxPay = new WXPay(config, domain + "/weChatPay/unifiedNotify", useSandbox);
        String outTradeNo = wxPay.generateGlobalOrder();
        Map<String, String> data = new HashMap<>(6);
        //商品描述
        data.put("body", description);
        //商户订单号
        data.put("out_trade_no", outTradeNo);
        //总金额（单位：分，沙盒测试就那么固定几个值：101(参考测试用例)）
        if (!useSandbox) {
            data.put("total_fee", String.valueOf(totalFee));
        } else {

            data.put("total_fee", "101");
        }
        //终端IP
        data.put("spbill_create_ip", IpUtil.getVisitorialIpAddr(request));
        //data.put("spbill_create_ip", "127.0.0.1");
        //交易类型
        data.put("trade_type", "JSAPI");
        //用户标识
        data.put("openid", openId);
        //data.put("openid", "ojWHx0sJ1zV5jhc9AdVwoYfYCnOM");
        Map<String, String> result = new HashMap<>(6);
        String prepayId;
        String sign;
        try {
            if (useSandbox) {
                config.setKey(wxPay.getSandboxSignKey());
            }
            Map<String, String> resultMap = wxPay.unifiedOrder(data);

            if (WXPayConstants.SUCCESS.equals(resultMap.get(WXPayConstants.RETURN_CODE))) {
                prepayId = resultMap.get("prepay_id");
                result.put("appId", config.getAppID());
                result.put("timeStamp", Long.toString(System.currentTimeMillis()));
                result.put("nonceStr", WXPayUtil.generateNonceStr());
                result.put("package", "prepay_id=" + prepayId);
                result.put("signType", wxPay.getSignType().toString());
                sign = WXPayUtil.generateSignature(result, config.getKey());
                result.put("paySign", sign);
            } else {
                return Message.fail("下单失败！", result.get(WXPayConstants.RETURN_MSG));
            }
        } catch (Exception e) {
            log.error("下单失败！", e);
            return Message.fail("下单失败！", e);
        }
        //新增商户订单信息
        WeChatPay weChatPay = new WeChatPay() {{
            setCreateTime(new Date());
            setFeeType("CNY");
            setMchId(config.getMchID());
            setOpenId(openId);
            setOutTradeNo(outTradeNo);
            setPrepayId(prepayId);
            setSign(sign);
            setTotalFee(BigDecimal.valueOf(totalFee));
            setTradeState(WXPayConstants.NOT_PAY);
            setUpdateTime(new Date());
            setRemark(description);
            setData(paramData);
            setWeChatUserId(ApplicationUtil.getSessionUser().getWeChatUserId());
            Map map = (Map) JSON.parse(paramData);
//            {"cardID":"510202195008162140","name":"钱素芳","phone":"18875198755","payAmt":"3.5","payNo":1567131038376,"day":"2019-08-30","asRowid":"56","deptID":"81","examId":"8214"}
            if (map.containsKey("day")) {
                setRegisterTime(DateUtil.changeStrToDate2(map.get("day").toString()));
            }
        }};
        weChatPayMapper.insert(weChatPay);
        result.put("id", weChatPay.getId().toString());
        return Message.success("下单成功！", result);
    }

    /**
     * 统一下单支付结果通知
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/unifiedNotify")
    public void unifiedNotify(HttpServletRequest request, HttpServletResponse response) {
        log.info("统一下单回调");
        InputStream inputStream = null;
        String respStr = "";
        try {
            //获取请求的流信息(这里是微信发的xml格式所有只能使用流来读)
            inputStream = request.getInputStream();
            String xml = WXPayUtil.inputStream2String(inputStream, "UTF-8");
            Map<String, String> notifyMap = WXPayUtil.xmlToMap(xml);

            if (notifyMap.get("return_code").equals("SUCCESS")) {
                if (notifyMap.get("result_code").equals("SUCCESS")) {
                    WXPay wxPay = new WXPay(config);
                    if (wxPay.isPayResultNotifySignatureValid(notifyMap)) {
                        //实际支付的订单金额:单位 分
                        BigDecimal totalFee = BigDecimal.valueOf(Long.parseLong(notifyMap.get("total_fee")));
                        //商户订单号
                        String outTradeNo = notifyMap.get("out_trade_no");
                        WeChatPay weChatPay = weChatPayMapper.selectOne(new WeChatPay() {{
                            setOutTradeNo(outTradeNo);
                            setTotalFee(totalFee);
                            setTradeState(WXPayConstants.NOT_PAY);
                        }});
                        if (weChatPay != null) {
                            weChatPayMapper.updateByPrimaryKeySelective(new WeChatPay() {{
                                setId(weChatPay.getId());
                                setTradeState(WXPayConstants.SUCCESS_PAY);
                                setUpdateTime(new Date());
                                //TODO 支付成功 修改数据
                                String data = weChatPay.getData();
                                PreviewRegisterUtil.submitRegister(((Map) JSON.parseObject(data)));

                            }});
                        }
                        //将分转换成元-实际支付金额:元
                        //BigDecimal amountPay = (new BigDecimal(amountpaid).divide(new BigDecimal("100"))).setScale(2);
                        //String openid = notifyMap.get("openid");  //如果有需要可以获取
                        //String trade_type = notifyMap.get("trade_type");
                        //告诉微信服务器收到信息了，不要在调用回调action了========这里很重要回复微信服务器信息用流发送一个xml即可
                        respStr="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg" +
                                "><![CDATA[OK" +
                                "]]></return_msg></xml>";
                    } else {
                        log.info("签名验证失败！");
                    }
                }
            }
            if (StringUtil.isBlank(respStr)) {
                respStr = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg" +
                        "><![CDATA[fail]]></return_msg></xml>";
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            respStr = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg" +
                    "><![CDATA[fail]]></return_msg></xml>";
        } finally {
            try {
                response.getWriter().write(respStr);
                if (inputStream!= null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * 查询订单
     * 使用场景：
     * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
     * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
     * ◆ 调用付款码支付API，返回USERPAYING的状态；
     * ◆ 调用关单或撤销接口API之前，需确认支付状态；
     *
     * @param paramName  transaction_id or out_trade_no（可以选用枚举来控制值的输入）
     * @param paramValue
     * @return
     */
    @ResponseBody
    @RequestMapping("queryOrder")
    public Message queryOrder(String paramName, String paramValue) {
        WXPay wxPay = new WXPay(config, Boolean.parseBoolean(useSandbox));
        Map<String, String> data = new HashMap<>(1);
        //data.put("transaction_id", "appid");
        //data.put("out_trade_no", "appid");
        if (StringUtil.isBlank(paramName)) {
            paramName = "out_trade_no";
        }
        data.put(paramName, paramValue);
        try {
            Map<String, String> result = wxPay.orderQuery(data);
            if (WXPayConstants.SUCCESS.equals(result.get(WXPayConstants.RETURN_CODE))) {
                String tradeState = result.get("trade_state");
                if (!"NOTPAY".equals(tradeState)) {
                    //TODO 订单查询成功，数据修改
                    WeChatPay weChatPay = weChatPayMapper.selectOne(new WeChatPay() {{
                        setOutTradeNo(paramValue);
                        setMchId(result.get("mch_id"));
                        setTotalFee(BigDecimal.valueOf(Long.parseLong(result.get("total_fee"))));
                        setTradeState(2);
                    }});
                    if (weChatPay != null) {
                        if (WXPayConstants.SUCCESS.equals(tradeState)) {
                            String param = weChatPay.getData();
                            weChatPay.setTradeState(0);
                            PreviewRegisterUtil.submitRegister(((Map) JSON.parseObject(param)));
                        } else if ("PAYERROR".equals(tradeState)) {
                            weChatPay.setTradeState(6);
                        } else if ("REFUND".equals(tradeState)) {
                            weChatPay.setTradeState(1);
                        } else if ("CLOSED".equals(tradeState)) {
                            weChatPay.setTradeState(3);
                        }
                        weChatPayMapper.updateByPrimaryKeySelective(weChatPay);

                    }
                }
                return Message.success("查询订单成功！", result);
            } else {
                return Message.fail("查询订单失败！", result.get(WXPayConstants.RETURN_MSG));
            }
        } catch (Exception e) {
            log.error("查询订单失败！", e);
            return Message.fail("查询订单失败！", e);
        }
    }

    /**
     * 关闭订单
     * 使用场景：
     * 商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
     * 系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
     * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟）
     *
     * @param param out_trade_no 商户订单号
     * @return
     */
    @ResponseBody
    @RequestMapping("closeOrder")
    public Message closeOrder(String param) {
        WXPay wxPay = new WXPay(config, Boolean.parseBoolean(useSandbox));
        Map<String, String> data = new HashMap<>(1);
        //商户单号
        data.put("out_trade_no", param);
        try {
            Map<String, String> result = wxPay.closeOrder(data);
            if (WXPayConstants.SUCCESS.equals(result.get(WXPayConstants.RETURN_CODE))) {
                return Message.success("订单关闭成功！", result);
            } else {
                return Message.fail("关闭订单失败！", result.get(WXPayConstants.RETURN_MSG));
            }
        } catch (Exception e) {
            log.error("关闭订单失败！", e);
            return Message.fail("关闭订单失败！", e);
        }
    }

    /**
     * 退款
     *
     * @param weChatPayRefund
     * @return
     */
    @ResponseBody
    @RequestMapping("refund")
    public Message refund(WeChatPayRefund weChatPayRefund) {
        WXPay wxPay = new WXPay(config, null, domain + "/weChatPay/refundNotify", Boolean.parseBoolean(useSandbox));
        Map<String, String> data = new HashMap<>(7);
        String outTradeNo = weChatPayRefund.getOutTradeNo();
        data.put("out_trade_no", outTradeNo);
        //商户退款单号
        String outRefundNo = wxPay.generateGlobalOrder();
        data.put("out_refund_no", outRefundNo);
        data.put("total_fee", weChatPayRefund.getTotalFee().toString());
        data.put("refund_fee", weChatPayRefund.getRefundFee().toString());
        data.put("refund_fee_type", "CNY");
        String refundDesc = weChatPayRefund.getRefundDesc();
        if (StringUtil.isNotBlank(refundDesc)) {
            data.put("refund_desc", refundDesc);
        }
        //退款资金来源
        data.put("refund_account", "REFUND_SOURCE_RECHARGE_FUNDS");
        try {
            Map<String, String> result = wxPay.refund(data);
            if (WXPayConstants.SUCCESS.equals(result.get(WXPayConstants.RETURN_CODE))) {
                Integer applyId = weChatPayRefund.getApplyId();
                weChatPayRefundApplyMapper.updateByPrimaryKeySelective(new WeChatPayRefundApply() {{
                    setId(applyId);
                    setUpdateTime(new Date());
                    setUserId(ApplicationUtil.getSessionUserId());
                }});
                weChatPayRefund.setRefundFeeType("CNY");
                weChatPayRefund.setRefundAccount("REFUND_SOURCE_RECHARGE_FUNDS");
                weChatPayRefund.setOutRefundNo(outRefundNo);
                weChatPayRefund.setRefundTime(new Date());
                weChatPayRefundMapper.insert(weChatPayRefund);
                return Message.success("申请退款成功！", result);
            } else {
                return Message.fail("申请退款失败！", result.get(WXPayConstants.RETURN_MSG));
            }
        } catch (Exception e) {
            log.error("申请退款失败！", e);
            return Message.fail("申请退款失败！", e);
        }
    }

    /**
     * 退款结果通知
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/refundNotify")
    @Transactional(rollbackFor = Exception.class)
    public void refundNotify(HttpServletRequest request, HttpServletResponse response) {
        log.info("退款回调");
        InputStream inputStream;
        try {
            //获取请求的流信息(这里是微信发的xml格式所有只能使用流来读)
            inputStream = request.getInputStream();
            String xml = WXPayUtil.inputStream2String(inputStream, "UTF-8");
            Map<String, String> notifyMap = WXPayUtil.xmlToMap(xml);

            if (notifyMap.get("return_code").equals("SUCCESS")) {
                if (notifyMap.get("result_code").equals("SUCCESS")) {

                    //加密信息
                    String reqInfo = notifyMap.get("req_info");
                    byte[] decode = Base64.getDecoder().decode(reqInfo);
                    String s = WXPayUtil.aesDdecrypt(decode, config);
                    Map<String, String> result = WXPayUtil.xmlToMap(s);

                    //商户订单号
                    String outTradeNo = result.get("out_trade_no");
                    //商户退款单号
//                    String outRefundNo = result.get("out_refund_no");
//                    BigDecimal totalFee = BigDecimal.valueOf(Long.parseLong(result.get("total_fee")));
                    BigDecimal refundFee = BigDecimal.valueOf(Long.parseLong(result.get("refund_fee")));
                    String refundStatus = result.get("refund_status");
                    WeChatPayRefundApply weChatPayRefundApply =
                            weChatPayRefundApplyMapper.getRefundInfo(new WeChatPayRefundApply() {{
                        setFee(refundFee);
                        setOutTradeNo(outTradeNo);
                        setStatus(1);
                    }});
                    if (weChatPayRefundApply != null) {
                        weChatPayRefundApply.setStatus(WXPayConstants.SUCCESS.equals(refundStatus) ? 2 : 3);
                        weChatPayRefundApply.setRemark(weChatPayRefundApply.getRemark()+ "       退款原因：" +weChatPayRefundApply.getRefundDesc());
                        weChatPayRefundApplyMapper.updateByPrimaryKeySelective(weChatPayRefundApply);
                        // TODO 退款成功,数据修改
                    }
                }
            }
            //告诉微信服务器收到信息了，不要在调用回调action了========这里很重要回复微信服务器信息用流发送一个xml即可
            response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK" +
                    "]]></return_msg></xml>");
            inputStream.close();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    /**
     * 退款查询
     *
     * @param paramName  refund_id、out_refund_no、out_trade_no、transaction_id四个参数必填一个
     * @param paramValue
     * @param offset     偏移量
     * @return
     */
    @ResponseBody
    @RequestMapping("refundQuery")
    public Message refundQuery(String paramName, String paramValue, Integer offset) {
        WXPay wxPay = new WXPay(config);
        Map<String, String> data = new HashMap<>(7);
        if (StringUtil.isBlank(paramName)) {
            paramName = "out_refund_no";
        }
        data.put(paramName, paramValue);
        if (offset != null) {
            data.put("offset", offset.toString());
        }
        try {
            Map<String, String> result = wxPay.refundQuery(data);
            if (WXPayConstants.SUCCESS.equals(result.get(WXPayConstants.RETURN_CODE))) {
                //TODO 数据修改

                return Message.success("查询成功！", result);
            } else {
                return Message.fail("查询失败！", result.get(WXPayConstants.RETURN_MSG));
            }
        } catch (Exception e) {
            log.error("查询退款详情失败！", e);
            return Message.fail("查询失败！");
        }
    }

    /**
     * 申请商户退款
     *
     * @param weChatPayRefundApply
     * @return
     */
    @ResponseBody
    @RequestMapping("refundApply")
    public Message refundApply(WeChatPayRefundApply weChatPayRefundApply) {
        weChatPayRefundApply.setCreateTime(new Date());
        //申请中
        weChatPayRefundApply.setStatus(1);
        weChatPayRefundApplyMapper.insert(weChatPayRefundApply);
        return Message.success("申请成功，等待管理员审核！");
    }

    /**
     * 商户拒绝退款
     *
     * @param id
     * @param remark 拒绝理由
     * @return
     */
    @ResponseBody
    @RequestMapping("refundDeny")
    public Message refundDeny(Integer id, String remark) {
        WeChatPayRefundApply weChatPayRefundApply = weChatPayRefundApplyMapper.selectByPrimaryKey(id);
        if (StringUtil.isNotBlank(remark)) {
            weChatPayRefundApply.setRemark(weChatPayRefundApply.getRemark() + "     拒绝原因：" + remark);
        }
        weChatPayRefundApply.setStatus(0);
        weChatPayRefundApply.setUserId(ApplicationUtil.getSessionUserId());
        weChatPayRefundApply.setUpdateTime(new Date());
        weChatPayRefundApplyMapper.updateByPrimaryKeySelective(weChatPayRefundApply);
        return Message.success("操作成功！");
    }

    /**
     * 查询商户所有退款申请
     *
     * @param weChatPayRefundApply
     * @return
     */
    @ResponseBody
    @RequestMapping("listRefundApply")
    public Message listRefundApply(WeChatPayRefundApply weChatPayRefundApply) {

        return Message.success("查询成功！", weChatPayRefundApplyMapper.listRefundApply(weChatPayRefundApply));
    }

    /**
     * 查询商户所有订单
     *
     * @param weChatPay
     * @return
     */
    @ResponseBody
    @RequestMapping("listOrder")
    public Message listOrder(WeChatPay weChatPay) {
        return Message.success("查询成功！", weChatPayMapper.listOrder(weChatPay));
    }

    /**
     * 根据商户订单号查询订单详情
     *
     * @param outTradeNo
     * @return
     */
    @ResponseBody
    @RequestMapping("getOrderByOutTradeNo")
    public Message getOrderByOutTradeNo(String outTradeNo) {
        return Message.success("获取成功！", weChatPayMapper.selectOne(new WeChatPay(){{
            setOutTradeNo(outTradeNo);
        }}));
    }

    /**
     * 删除商户支付订单
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteWeChatPayById")
    public Message deleteWeChatPayById(Integer id) {
        return Message.success("删除成功！", weChatPayMapper.deleteByPrimaryKey(id));
    }

    /**
     * 查询所有挂号信息
     *
     * @param weChatPay 查询条件
     * @return
     */
    @ResponseBody
    @RequestMapping("listWeChatPayForRegister")
    public Message listWeChatPayForRegister(WeChatPay weChatPay){
        return Message.success("请求成功", weChatPayMapper.listWeChatPayForRegister(weChatPay));
    }

}