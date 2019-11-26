package com.jhg.marketing.web.util.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Locale;

/**
 * @author lxl
 * @since 2019/8/27 14:08
 */
@Slf4j
public class MyLogOutFilter extends LogoutFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = this.getSubject(request, response);
        if (this.isPostOnlyLogout() && !WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
            return this.onLogoutRequestNotAPost(request, response);
        } else {
            String redirectUrl = this.getRedirectUrl(request, response, subject);

            try {
                log.debug("------注销账号！");
                //添加成功之后 清除缓存
                DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
                CustomRealm shiroRealm = (CustomRealm) securityManager.getRealms().iterator().next();
                //清除权限 相关的缓存
                shiroRealm.clearAllCache();
                subject.logout();
            } catch (SessionException var6) {
                log.debug("Encountered session exception during logout.  This can generally safely be ignored.", var6);
            }

            this.issueRedirect(request, response, redirectUrl);
            return false;
        }
    }
}
