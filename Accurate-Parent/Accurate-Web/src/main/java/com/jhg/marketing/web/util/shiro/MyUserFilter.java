package com.jhg.marketing.web.util.shiro;

import com.jhg.marketing.web.util.WeChatUtil;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author lxl
 * @since 2019/7/30 16:10
 */
public class MyUserFilter extends UserFilter {


    @Override
    protected void redirectToLogin(ServletRequest req, ServletResponse resp)
            throws IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String loginUrl;
        if (WeChatUtil.isWeChat(request)) {
            String redirectUri = request.getRequestURI();
            if (null != request.getQueryString()) {
                redirectUri += "?" + request.getQueryString();
            }
            loginUrl = "/authorize?redirectUri=" + URLEncoder.encode(redirectUri);
        } else {
            loginUrl = "/admin/user/login";
        }
        WebUtils.issueRedirect(request, response, loginUrl);
    }
}
