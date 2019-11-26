package com.jhg.marketing.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.dao.domin.SysUser;
import com.jhg.marketing.dao.domin.WeChatUser;
import com.jhg.marketing.dao.domin.WebConfig;
import com.jhg.marketing.dao.mapper.SysUserMapper;
import com.jhg.marketing.dao.mapper.WeChatUserMapper;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.ApplicationUtil;
import com.jhg.marketing.web.util.CheckUtil;
import com.jhg.marketing.web.util.StringUtil;
import com.jhg.marketing.web.util.ehcache.EhcacheUtil;
import com.jhg.marketing.web.util.hisInterface.WeChatInterfaceUtil;
import com.jhg.marketing.web.util.message.ImageMessageUtil;
import com.jhg.marketing.web.util.message.MessageUtil;
import com.jhg.marketing.web.util.message.TextMessageUtil;
import com.jhg.marketing.web.util.note.LinkWS;
import com.jhg.marketing.web.util.note.LinkWSSoap;
import com.jhg.marketing.web.util.shiro.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.TransactionAnnotationParser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 与微信对接登陆验证
 *
 * @author lxl
 */
@Slf4j
@Controller
public class LoginController {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private WebConfig webConfig;
    @Autowired
    private ImageMessageUtil imageMessageUtil;
    @Value("${user.domain}")
    private String domain;
    @Value("${user.default.uploadpath}")
    private String uploadPath;

    @RequestMapping(value = "wx", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始微信验证");
        //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            if (CheckUtil.checkSignature(webConfig.getToken(), signature, timestamp, nonce)) {
                out.write(echostr);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.info(e.getMessage());
        } finally {
            out.close();
        }

    }

    @RequestMapping(value = "wx", method = RequestMethod.POST)
    public void dopost(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String, String> map = MessageUtil.xmlToMap(request);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");
        String EventKey = map.get("EventKey");

        String message = null;
        //处理文本类型，实现输入1，回复相应的封装的内容
        TextMessageUtil textMessage = new TextMessageUtil();
//        if ("text".equals(MsgType)) {
//            if ("1".equals(Content)) {
//                message = textMessage.initMessage(FromUserName, ToUserName);
//            } else if ("图片".equals(Content)) {
////				ImageMessageUtil imageMessageUtil = new ImageMessageUtil();
//                message = imageMessageUtil.initMessage(FromUserName, ToUserName);
//            } else {
//                message = textMessage.initMessage(FromUserName, ToUserName, Content);
//            }
//        } else if ("event".equals(MsgType)) {
//            if ("11".equals(EventKey)) {
//                message = textMessage.initMessage(FromUserName, ToUserName, "您正在点击往期活动按钮");
//            }
//        } else {
//            message = textMessage.initMessage(FromUserName, ToUserName, "你在弄啥呢？");
//        }
        try {
            out = response.getWriter();
            if (message == null) {
                message = "";
            }
//            System.out.println(message);
            out.write(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.info(e.getMessage());
        }
        out.close();
    }

    /**
     * 创建微信授权路径
     *
     * @return
     */
    @RequestMapping("/authorize")
    public String authorize(String redirectUri, HttpServletRequest request) {
        log.info("requestUrl:" + request.getRequestURL());
        String url = domain + "/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, StringUtil.isBlank(redirectUri) ? null : URLEncoder.encode(redirectUri));
        return "redirect:" + redirectUrl;
    }

    /**
     * 微信用户模拟登陆
     *
     * @param code
     * @param session
     * @return
     */
    @RequestMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam(value = "state", required = false, defaultValue = "") String redirectUri, HttpSession session) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        WxMpUser wxMpUser = new WxMpUser();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        } catch (WxErrorException e) {
            log.error("wechat error{}", e.getError().getErrorMsg());
            //手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "登陆失败，请刷新或重新进入此页面！";
        }
        //获取openId
        assert wxMpUser != null;
        String openId = wxMpUser.getOpenId();
        session.setAttribute("wxMpUser", wxMpUser);
        //本地有此用户，查询his是否绑定此用户
//		JSONObject jsonObject = WeChatInterfaceUtil.getBindUserInfo(openId);
        //从本地查询是否有用户信息
        SysUser sysUser = sysUserMapper.listUserByOpenId(openId);
        if (sysUser == null) {
            return "redirect:/register?openId=" + openId +"&redirectUri="+redirectUri;
        }
//		if (WeChatInterfaceUtil.judgeResult(jsonObject)) {
//			if (sysUser == null) {
//				//本地和his都没数据，跳转注册界面
//				return "redirect:/register?openId=" + openId;
//			} else {
//				//本地有信息，his无，绑定信息到his
//				JSONObject bindUserInfo = WeChatInterfaceUtil.bindUserInfo(openId, sysUser.getTrueName(), sysUser.getTelephone(), sysUser.getIdCard());
//				if (WeChatInterfaceUtil.judgeResult(bindUserInfo)) {
//					return "绑定信息失败，请刷新或重新进入此页面！";
//				}
//			}
//		} else {
//			if (sysUser == null) {
//				List<String> list = JSONObject.parseArray(jsonObject.getString("data"), String.class);
//				if (list == null) {
//					return "redirect:/register?openId=" + openId;
//				}
//				//本地无，his有，绑定信息到本地
//				SysUser user = new SysUser();
//				user.setPassword(openId);
//				user.setRegisterTime(new Date());
//				user.setEnabled(true);
//				user.setGender(wxMpUser.getSexDesc());
//				user.setUsername(wxMpUser.getNickname());
//				user.setHeadImgUrl(wxMpUser.getHeadImgUrl());
//				sysUserMapper.insert(user);
//				//TODO 查询用户最新住院号
//				WeChatUser weChatUser = new WeChatUser();
//				weChatUser.setIdCard(JSON.parseObject(list.get(0)).getString("CARD"));
//				weChatUser.setMainFlag(1);
//				weChatUser.setUserId(user.getId());
//				weChatUser.setTelephone(JSON.parseObject(list.get(0)).getString("PHONE"));
//				weChatUser.setTrueName(JSON.parseObject(list.get(0)).getString("XINGMING"));
//				weChatUserMapper.insert(weChatUser);
//			}
//		}
        boolean login = login(openId, openId);
        if (login) {
            if (StringUtil.isNotBlank(redirectUri)) {
                return "redirect:" + domain + URLDecoder.decode(redirectUri);
            }
            //个人中心
            return "redirect:" + domain + "/personalCenterThree?openid=" + openId;
        } else {
            return "登陆失败，请刷新或重新进入此页面！";
        }
    }

    public boolean login(String username, String password) {
        try {
            Subject subject = ShiroUtil.getSubject();
            subject.login(new UsernamePasswordToken(username, password));
            SysUser user = (SysUser) subject.getPrincipal();
            Integer userId = user.getId();
            Session session = subject.getSession();
            //查询完整用户信息，设置到session中
            session.setAttribute("user", sysUserMapper.getUserInfo(userId));
            SysUser userParam = new SysUser();
            userParam.setLastLoginTime(new Date());
            userParam.setId(userId);
            sysUserMapper.updateByPrimaryKeySelective(userParam);
            System.out.println("登陆成功");
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    @Autowired
    private WeChatUserMapper weChatUserMapper;

    /**
     * 绑定用户信息
     *
     * @param sysUser
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/bindUserInfo")
    @Transactional(rollbackFor = Exception.class)
    public Message bindUserInfo(SysUser sysUser, HttpSession session) {
        String telephone = sysUser.getTelephone();
        if (StringUtil.isBlank(telephone)) {
            return Message.fail("电话号码不能为空！");
        }
        String idCard = sysUser.getIdCard();
        if (StringUtil.isBlank(idCard)) {
            return Message.fail("身份证号不能为空！");
        }
        String trueName = sysUser.getTrueName();
        if (StringUtil.isBlank(trueName)) {
            return Message.fail("真实姓名不能为空！");
        }
        String captcha = sysUser.getCaptcha();
        if (StringUtil.isBlank(captcha)) {
            return Message.fail("验证码不能为空！");
        }
        //新增用户信息
        String openId = sysUser.getOpenId();
        boolean islogin = false;
        if (openId == null) {
            islogin = true;
            openId = ((WxMpUser) session.getAttribute("wxMpUser")).getOpenId();
        }
        EhcacheUtil ehcacheUtil = new EhcacheUtil();
        WxMpUser wxMpUser = (WxMpUser) session.getAttribute("wxMpUser");
        String code = ehcacheUtil.get(openId + "captcha");
        if (captcha.equals(code)) {
            sysUser.setPassword(openId);
            sysUser.setOpenId(openId);
            sysUser.setRegisterTime(new Date());
            sysUser.setLastLoginTime(new Date());
            sysUser.setEnabled(true);
            sysUser.setGender(wxMpUser.getSexDesc());
            sysUser.setUsername(wxMpUser.getNickname());
            sysUser.setHeadImgUrl(wxMpUser.getHeadImgUrl());
            sysUser.setType(2);
            sysUserMapper.insert(sysUser);
            //TODO 查询用户最新住院号
            WeChatUser weChatUser = new WeChatUser();
            weChatUser.setIdCard(idCard);
            weChatUser.setMainFlag(1);
            weChatUser.setUserId(sysUser.getId());
            weChatUser.setTelephone(telephone);
            weChatUser.setTrueName(trueName);
            weChatUser.setEnable(false);
            weChatUserMapper.insert(weChatUser);
            //绑定信息到his
            JSONObject jsonObject = WeChatInterfaceUtil.bindUserInfo(openId, trueName, telephone, idCard);
            if (WeChatInterfaceUtil.judgeResult(jsonObject)) {
                //手动回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Message.fail("绑定失败，请重新绑定或刷新页面后重试！");
            }
            //模拟登陆
            if (!islogin) {
                login(openId, openId);
            }
            return Message.success("绑定成功！");
        } else {
            return Message.fail("验证码错误，请重新输入！");
        }
    }

    @Value("${user.company-name}")
    private String companyName;

    /**
     * 发送验证码
     *
     * @param telephone
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendCaptcha")
    public Message sendCaptcha(String telephone, String openId) {
        if (telephone == null || "".equals(telephone)) {
            return Message.fail("请输入手机号码！");
        }
        if (StringUtil.isBlank(openId)) {
            openId = ApplicationUtil.getSessionOpenId();
        }
        //获取验证码
        EhcacheUtil ehcacheUtil = new EhcacheUtil();
        String code = ehcacheUtil.get(openId + "captcha");
        if (code == null || "".equals(code)) {
            code = (int) (Math.random() * 9000 + 1000) + "";
        }
        LinkWS linkWS = new LinkWS();
        LinkWSSoap linkWSSoap = linkWS.getLinkWSSoap();
        String batchSend = linkWSSoap.batchSend2("JHG001901", "jhg2016!@", telephone, "您的验证码为：" + code + "【" + companyName + "】", null, null);
        if (Integer.parseInt(batchSend) > 0) {
            ehcacheUtil.setex(openId + "captcha", code, 300);
            return Message.success("验证码发送成功，有效时间为5分钟，请注意查收!");
        } else {
            return Message.fail("验证码发送失败，请检查输入的手机号码是否无误！");
        }
    }

    /**
     * 生成PDF
     *
     * @param attachments
     * @return
     */
    @ResponseBody
    @RequestMapping("/pdf")
    public Message pdf(@RequestParam(value = "file", required = false) String attachments) {
        if (attachments == null) {
            return Message.fail("生成PDF失败");
        }
        BASE64Decoder decoder = new BASE64Decoder();
        String fileName;
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(attachments);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            fileName = UUID.randomUUID().toString() + ".pdf";
            String filePath = uploadPath + "/pdf/";
            File uploadDir = new File(filePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            OutputStream out = new FileOutputStream(filePath + fileName);
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Message.fail("生成PDF失败");
        }
        return Message.success("生成PDF成功", fileName);
    }

    /**
     * 下载PDF
     *
     * @param fileName
     * @param response
     */
    @RequestMapping("/download")
    public void download(String fileName, HttpServletResponse response) {
        OutputStream outputStream;
        try {
            outputStream = response.getOutputStream();
            //清空下载文件的空白行（空白行是因为有的前端代码编译后产生的）
            response.reset();
            //设置响应头，把文件名字设置好
            response.setHeader("Content-Disposition", "attachment; filename=pdf.pdf");
            //解决编码问题
            response.setContentType("application/octet-stream; charset=utf-8");
            File file = new File(uploadPath + "/pdf", fileName);
            outputStream.write(FileUtils.readFileToByteArray(file));
            outputStream.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }
}