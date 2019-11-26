package com.jhg.marketing.web.config;

import com.jhg.marketing.dao.domin.AccessToken;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.AccessTokenUtil;
import com.jhg.marketing.web.util.exception.WxErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
@RestController
public class GlobalExceptionHandler {

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    /**
     * 统一异常处理
     *
     * @param exception exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Message processException(Exception exception) {
        log.error("系统出现异常！", exception);
        return Message.fail("", exception);
    }

    /**
     * 微信异常处理
     *
     * @param exception exception
     * @return
     */
    @ExceptionHandler(WxErrorException.class)
    public Message weChatException(WxErrorException exception, HttpServletRequest request, HttpServletResponse response) {
        log.error("系统出现异常！", exception);
        if (exception.getError().getErrorCode() == 40001) {
            AccessToken token = accessTokenUtil.getAccessTokenByInterface();
            //TODO 再次请求接口返回数据？
//            ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
//            WebConfig webConfig = applicationContext.getBean(WebConfig.class);
//            request.getRequestURI()
            return Message.fail("请刷新页面后重试");
        }
        return Message.fail("", exception);
    }


}