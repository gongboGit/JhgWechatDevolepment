package com.jhg.marketing.web.util.weChatPay;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class WXPay {

    /**
     * 微信支付配置
     */
    private WXPayConfig config;

    /**
     * 签名类型
     */
    private WXPayConstants.SignType signType;

    /**
     * 自动上报
     */
    private boolean autoReport;

    /**
     * 是否使用沙盒测试
     */
    private boolean useSandbox;

    /**
     * 统一下单回调url
     */
    private String unifiedNotifyUrl;

    /**
     * 退款回调url
     */
    private String refundNotifyUrl;

    /**
     * 微信支付请求类型
     */
    private WXPayRequest wxPayRequest;

    public WXPay(final WXPayConfig config) {
        this(config, null,null, true, false);
    }

    public WXPay(final WXPayConfig config, final String unifiedNotifyUrl) {
        this(config, unifiedNotifyUrl, null, true, false);
    }

    public WXPay(final WXPayConfig config, final boolean useSandbox) {
        this(config, null,null, true, useSandbox);
    }

    public WXPay(final WXPayConfig config, final String unifiedNotifyUrl, final boolean useSandbox) {
        this(config, unifiedNotifyUrl, null, true, useSandbox);
    }

    public WXPay(final WXPayConfig config, final String unifiedNotifyUrl, final String refundNotifyUrl) {
        this(config, unifiedNotifyUrl, refundNotifyUrl, true, false);
    }

    public WXPay(final WXPayConfig config, final boolean autoReport, final boolean useSandbox) {
        this(config, null, null, autoReport, useSandbox);
    }

    public WXPay(final WXPayConfig config, final String unifiedNotifyUrl, final String refundNotifyUrl, final boolean useSandbox) {
        this(config, unifiedNotifyUrl, refundNotifyUrl, true, useSandbox);
    }

    public WXPay(final WXPayConfig config, final String unifiedNotifyUrl, final boolean autoReport, final boolean useSandbox) {
        this(config, unifiedNotifyUrl, null, autoReport, useSandbox);
    }

private WXPay(final WXPayConfig config, final String unifiedNotifyUrl, final String refundNotifyUrl, final boolean autoReport, final boolean useSandbox) {
        this.config = config;
        this.unifiedNotifyUrl = unifiedNotifyUrl;
        this.refundNotifyUrl = refundNotifyUrl;
        this.autoReport = autoReport;
        this.useSandbox = useSandbox;
        if (useSandbox) {
            this.signType = WXPayConstants.SignType.MD5;
        }
        else {
            this.signType = WXPayConstants.SignType.MD5;
        }
        this.wxPayRequest = new WXPayRequest(config);
    }

    /**requestWithoutCert
     * 验证参数是否合理
     *
     * @throws Exception
     */
    private void checkWXPayConfig() throws Exception {
        if (this.config == null) {
            throw new Exception("config is null");
        }
        if (this.config.getAppID() == null || this.config.getAppID().trim().length() == 0) {
            throw new Exception("appid in config is empty");
        }
        if (this.config.getMchID() == null || this.config.getMchID().trim().length() == 0) {
            throw new Exception("appid in config is empty");
        }
        if (this.config.getCertStream() == null) {
            throw new Exception("cert stream in config is empty");
        }
        if (this.config.getWXPayDomain() == null){
            throw new Exception("config.getWXPayDomain() is null");
        }

        if (this.config.getHttpConnectTimeoutMs() < 10) {
            throw new Exception("http connect timeout is too small");
        }
        if (this.config.getHttpReadTimeoutMs() < 10) {
            throw new Exception("http read timeout is too small");
        }

    }

    /**
     * 向 Map 中添加 appid、mch_id、nonce_str、sign_type、sign <br>
     * 该函数适用于商户适用于统一下单等接口，不适用于红包、代金券接口
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    public Map<String, String> fillRequestData(Map<String, String> reqData) throws Exception {
        reqData.put("appid", config.getAppID());
        reqData.put("mch_id", config.getMchID());
        reqData.put("nonce_str", WXPayUtil.generateNonceStr());
        if (WXPayConstants.SignType.MD5.equals(this.signType)) {
            reqData.put("sign_type", WXPayConstants.MD5);
        }
        else if (WXPayConstants.SignType.HMACSHA256.equals(this.signType)) {
            reqData.put("sign_type", WXPayConstants.HMACSHA256);
        }
        reqData.put("sign", WXPayUtil.generateSignature(reqData, config.getKey(), this.signType));
        return reqData;
    }

    /**
     * 判断xml数据的sign是否有效，必须包含sign字段，否则返回false。
     *
     * @param reqData 向wxpay post的请求数据
     * @return 签名是否有效
     * @throws Exception
     */
    public boolean isResponseSignatureValid(Map<String, String> reqData) throws Exception {
        // 返回数据的签名方式和请求中给定的签名方式是一致的
        return WXPayUtil.isSignatureValid(reqData, this.config.getKey(), this.signType);
    }

    /**
     * 判断支付结果通知中的sign是否有效
     *
     * @param reqData 向wxpay post的请求数据
     * @return 签名是否有效
     * @throws Exception
     */
    public boolean isPayResultNotifySignatureValid(Map<String, String> reqData) throws Exception {
        String signTypeInData = reqData.get(WXPayConstants.FIELD_SIGN_TYPE);
        WXPayConstants.SignType signType;
    if (signTypeInData == null) {
            signType = WXPayConstants.SignType.MD5;
        }
        else {
            signTypeInData = signTypeInData.trim();
            if (signTypeInData.length() == 0) {
                signType = WXPayConstants.SignType.MD5;
            }
            else if (WXPayConstants.MD5.equals(signTypeInData)) {
                signType = WXPayConstants.SignType.MD5;
            }
            else if (WXPayConstants.HMACSHA256.equals(signTypeInData)) {
                signType = WXPayConstants.SignType.HMACSHA256;
            }
            else {
                throw new Exception(String.format("Unsupported sign_type: %s", signTypeInData));
            }
        }
        return WXPayUtil.isSignatureValid(reqData, this.config.getKey(), signType);
    }


    /**
     * 不需要证书的请求
     * @param urlSuffix String
     * @param reqData 向wxpay post的请求数据
     * @param connectTimeoutMs 超时时间，单位是毫秒
     * @param readTimeoutMs 超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public String requestWithoutCert(String urlSuffix, Map<String, String> reqData,
                                     int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String msgUUID = reqData.get("nonce_str");
        String reqBody = WXPayUtil.mapToXml(reqData);

        String resp = this.wxPayRequest.requestWithoutCert(urlSuffix, msgUUID, reqBody, connectTimeoutMs, readTimeoutMs, autoReport);
        return resp;
    }


    /**
     * 需要证书的请求
     * @param urlSuffix String
     * @param reqData 向wxpay post的请求数据  Map
     * @param connectTimeoutMs 超时时间，单位是毫秒
     * @param readTimeoutMs 超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public String requestWithCert(String urlSuffix, Map<String, String> reqData,
                                  int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String msgUUID= reqData.get("nonce_str");
        String reqBody = WXPayUtil.mapToXml(reqData);

        String resp = this.wxPayRequest.requestWithCert(urlSuffix, msgUUID, reqBody, connectTimeoutMs, readTimeoutMs, this.autoReport);
        return resp;
    }

    /**
     * 处理 HTTPS API返回数据，转换成Map对象。return_code为SUCCESS时，验证签名。
     * @param xmlStr API返回的XML格式数据
     * @return Map类型数据
     * @throws Exception
     */
    public Map<String, String> processResponseXml(String xmlStr) throws Exception {
        String RETURN_CODE = "return_code";
        String return_code;
        Map<String, String> respData = WXPayUtil.xmlToMap(xmlStr);
        if (respData.containsKey(RETURN_CODE)) {
            return_code = respData.get(RETURN_CODE);
        }
        else {
            throw new Exception(String.format("No `return_code` in XML: %s", xmlStr));
        }

        if (return_code.equals(WXPayConstants.FAIL)) {
            return respData;
        }
        else if (return_code.equals(WXPayConstants.SUCCESS)) {
           if (this.isResponseSignatureValid(respData)) {
               return respData;
           }
           else {
               throw new Exception(String.format("Invalid sign value in XML: %s", xmlStr));
           }
        }
        else {
            throw new Exception(String.format("return_code value %s is invalid in XML: %s", return_code, xmlStr));
        }
    }

    /**
     * 作用：提交刷卡支付<br>
     * 场景：刷卡支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> microPay(Map<String, String> reqData) throws Exception {
        return this.microPay(reqData, this.config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：提交刷卡支付<br>
     * 场景：刷卡支付
     * @param reqData 向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs 读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> microPay(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WXPayConstants.SANDBOX_MICROPAY_URL_SUFFIX;
        }
        else {
            url = WXPayConstants.MICROPAY_URL_SUFFIX;
        }
        String respXml = this.requestWithoutCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }

    /**
     * 提交刷卡支付，针对软POS，尽可能做成功
     * 内置重试机制，最多60s
     * @param reqData
     * @return
     * @throws Exception
     */
    public Map<String, String> microPayWithPos(Map<String, String> reqData) throws Exception {
        return this.microPayWithPos(reqData, this.config.getHttpConnectTimeoutMs());
    }

    /**
     * 提交刷卡支付，针对软POS，尽可能做成功
     * 内置重试机制，最多60s
     * @param reqData
     * @param connectTimeoutMs
     * @return
     * @throws Exception
     */
    public Map<String, String> microPayWithPos(Map<String, String> reqData, int connectTimeoutMs) throws Exception {
        int remainingTimeMs = 60*1000;
        long startTimestampMs = 0;
        Map<String, String> lastResult = null;
        Exception lastException = null;

        while (true) {
            startTimestampMs = WXPayUtil.getCurrentTimestampMs();
            int readTimeoutMs = remainingTimeMs - connectTimeoutMs;
            if (readTimeoutMs > 1000) {
                try {
                    lastResult = this.microPay(reqData, connectTimeoutMs, readTimeoutMs);
                    String returnCode = lastResult.get("return_code");
                    if (returnCode.equals("SUCCESS")) {
                        String resultCode = lastResult.get("result_code");
                        String errCode = lastResult.get("err_code");
                        if (resultCode.equals("SUCCESS")) {
                            break;
                        }
                        else {
                            // 看错误码，若支付结果未知，则重试提交刷卡支付
                            if (errCode.equals("SYSTEMERROR") || errCode.equals("BANKERROR") || errCode.equals("USERPAYING")) {
                                remainingTimeMs = remainingTimeMs - (int)(WXPayUtil.getCurrentTimestampMs() - startTimestampMs);
                                if (remainingTimeMs <= 100) {
                                    break;
                                }
                                else {
                                    WXPayUtil.getLogger().info("microPayWithPos: try micropay again");
                                    if (remainingTimeMs > 5*1000) {
                                        Thread.sleep(5*1000);
                                    }
                                    else {
                                        Thread.sleep(1*1000);
                                    }
                                    continue;
                                }
                            }
                            else {
                                break;
                            }
                        }
                    }
                    else {
                        break;
                    }
                }
                catch (Exception ex) {
                    lastResult = null;
                    lastException = ex;
                }
            }
            else {
                break;
            }
        }

        if (lastResult == null) {
            throw lastException;
        }
        else {
            return lastResult;
        }
    }



    /**
     * 作用：统一下单<br>
     * 场景：公众号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> unifiedOrder(Map<String, String> reqData) throws Exception {
        return this.unifiedOrder(reqData, config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：统一下单<br>
     * 场景：公众号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs 读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> unifiedOrder(Map<String, String> reqData,  int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WXPayConstants.SANDBOX_UNIFIEDORDER_URL_SUFFIX;
        }
        else {
            url = WXPayConstants.UNIFIEDORDER_URL_SUFFIX;
        }
        if(this.unifiedNotifyUrl != null) {
            reqData.put("notify_url", this.unifiedNotifyUrl);
        }
        String respXml = this.requestWithoutCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }


    /**
     * 作用：查询订单<br>
     * 场景：刷卡支付、公众号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> orderQuery(Map<String, String> reqData) throws Exception {
        return this.orderQuery(reqData, config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：查询订单<br>
     * 场景：刷卡支付、公众号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据 int
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs 读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> orderQuery(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WXPayConstants.SANDBOX_ORDERQUERY_URL_SUFFIX;
        }
        else {
            url = WXPayConstants.ORDERQUERY_URL_SUFFIX;
        }
        String respXml = this.requestWithoutCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }


    /**
     * 作用：撤销订单<br>
     * 场景：刷卡支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> reverse(Map<String, String> reqData) throws Exception {
        return this.reverse(reqData, config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：撤销订单<br>
     * 场景：刷卡支付<br>
     * 其他：需要证书
     * @param reqData 向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs 读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> reverse(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WXPayConstants.SANDBOX_REVERSE_URL_SUFFIX;
        }
        else {
            url = WXPayConstants.REVERSE_URL_SUFFIX;
        }
        String respXml = this.requestWithCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }


    /**
     * 作用：关闭订单<br>
     * 场景：公众号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> closeOrder(Map<String, String> reqData) throws Exception {
        return this.closeOrder(reqData, config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：关闭订单<br>
     * 场景：公众号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs 读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> closeOrder(Map<String, String> reqData,  int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WXPayConstants.SANDBOX_CLOSEORDER_URL_SUFFIX;
        }
        else {
            url = WXPayConstants.CLOSEORDER_URL_SUFFIX;
        }
        String respXml = this.requestWithoutCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }


    /**
     * 作用：申请退款<br>
     * 场景：刷卡支付、公众号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> refund(Map<String, String> reqData) throws Exception {
        return this.refund(reqData, this.config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：申请退款<br>
     * 场景：刷卡支付、公众号支付、扫码支付、APP支付<br>
     * 其他：需要证书
     * @param reqData 向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs 读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> refund(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WXPayConstants.SANDBOX_REFUND_URL_SUFFIX;
        }
        else {
            url = WXPayConstants.REFUND_URL_SUFFIX;
        }
        if (this.refundNotifyUrl != null) {
            reqData.put("notify_url",this.refundNotifyUrl);
        }
        String respXml = this.requestWithCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }


    /**
     * 作用：退款查询<br>
     * 场景：刷卡支付、公众号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> refundQuery(Map<String, String> reqData) throws Exception {
        return this.refundQuery(reqData, this.config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：退款查询<br>
     * 场景：刷卡支付、公众号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs 读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> refundQuery(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WXPayConstants.SANDBOX_REFUNDQUERY_URL_SUFFIX;
        }
        else {
            url = WXPayConstants.REFUNDQUERY_URL_SUFFIX;
        }
        String respXml = this.requestWithoutCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }


    /**
     * 作用：对账单下载（成功时返回对账单数据，失败时返回XML格式数据）<br>
     * 场景：刷卡支付、公众号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> downloadBill(Map<String, String> reqData) throws Exception {
        return this.downloadBill(reqData, this.config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：对账单下载<br>
     * 场景：刷卡支付、公众号支付、扫码支付、APP支付<br>
     * 其他：无论是否成功都返回Map。若成功，返回的Map中含有return_code、return_msg、data，
     *      其中return_code为`SUCCESS`，data为对账单数据。
     * @param reqData 向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs 读超时时间，单位是毫秒
     * @return 经过封装的API返回数据
     * @throws Exception
     */
    public Map<String, String> downloadBill(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WXPayConstants.SANDBOX_DOWNLOADBILL_URL_SUFFIX;
        }
        else {
            url = WXPayConstants.DOWNLOADBILL_URL_SUFFIX;
        }
        String respStr = this.requestWithoutCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs).trim();
        Map<String, String> ret;
        // 出现错误，返回XML数据
        if (respStr.indexOf("<") == 0) {
            ret = WXPayUtil.xmlToMap(respStr);
        }
        else {
            // 正常返回csv数据
            ret = new HashMap<String, String>();
            ret.put("return_code", WXPayConstants.SUCCESS);
            ret.put("return_msg", "ok");
            ret.put("data", respStr);
        }
        return ret;
    }


    /**
     * 作用：交易保障<br>
     * 场景：刷卡支付、公众号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> report(Map<String, String> reqData) throws Exception {
        return this.report(reqData, this.config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：交易保障<br>
     * 场景：刷卡支付、公众号支付、扫码支付、APP支付
     * @param reqData 向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs 读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> report(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WXPayConstants.SANDBOX_REPORT_URL_SUFFIX;
        }
        else {
            url = WXPayConstants.REPORT_URL_SUFFIX;
        }
        String respXml = this.requestWithoutCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return WXPayUtil.xmlToMap(respXml);
    }


    /**
     * 作用：转换短链接<br>
     * 场景：刷卡支付、扫码支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> shortUrl(Map<String, String> reqData) throws Exception {
        return this.shortUrl(reqData, this.config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：转换短链接<br>
     * 场景：刷卡支付、扫码支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> shortUrl(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WXPayConstants.SANDBOX_SHORTURL_URL_SUFFIX;
        }
        else {
            url = WXPayConstants.SHORTURL_URL_SUFFIX;
        }
        String respXml = this.requestWithoutCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }


    /**
     * 作用：授权码查询OPENID接口<br>
     * 场景：刷卡支付
     * @param reqData 向wxpay post的请求数据
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> authCodeToOpenid(Map<String, String> reqData) throws Exception {
        return this.authCodeToOpenid(reqData, this.config.getHttpConnectTimeoutMs(), this.config.getHttpReadTimeoutMs());
    }


    /**
     * 作用：授权码查询OPENID接口<br>
     * 场景：刷卡支付
     * @param reqData 向wxpay post的请求数据
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs 读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    public Map<String, String> authCodeToOpenid(Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs) throws Exception {
        String url;
        if (this.useSandbox) {
            url = WXPayConstants.SANDBOX_AUTHCODETOOPENID_URL_SUFFIX;
        }
        else {
            url = WXPayConstants.AUTHCODETOOPENID_URL_SUFFIX;
        }
        String respXml = this.requestWithoutCert(url, this.fillRequestData(reqData), connectTimeoutMs, readTimeoutMs);
        return this.processResponseXml(respXml);
    }


    /**
     * 获取沙盒API密钥
     *
     * @return 沙盒密钥
     * @throws Exception
     */
    public String getSandboxSignKey() throws Exception {
        Map<String, String> data = new HashMap<>(3);
        //商户号
        data.put("mch_id", config.getMchID());
        //随机字符串
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        //签名
        data.put("sign", WXPayUtil.generateSignature(data, config.getKey()));
        WXPay wxPay = new WXPay(config);
        String result = wxPay.requestWithoutCert("/sandboxnew/pay/getsignkey", data, 10000, 10000);
        return WXPayUtil.xmlToMap(result).get("sandbox_signkey");
    }

    /**
     * 生成唯一订单号
     *
     * @return
     */
    public String generateGlobalOrder() {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return (1 + String.format("%015d", hashCodeV));
    }

} // end class
