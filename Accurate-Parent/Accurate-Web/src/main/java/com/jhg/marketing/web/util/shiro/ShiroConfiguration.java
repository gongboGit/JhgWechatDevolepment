package com.jhg.marketing.web.util.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ShiroConfiguration {

    @Autowired
    protected ShiroFilterChainDefinition shiroFilterChainDefinition;

    @Value("#{ @environment['shiro.loginUrl'] ?: '/login.jsp' }")
    protected String loginUrl;

    @Value("#{ @environment['shiro.successUrl'] ?: '/' }")
    protected String successUrl;

    @Value("#{ @environment['shiro.unauthorizedUrl'] ?: null }")
    protected String unauthorizedUrl;

    @Value("${shiro.session.timeout}")
    private int serverSessionTimeout;
    @Value("${user.wechat.verify-file}")
    private String verifyFile;

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
     *
     * @return
     */
    @Bean
    public Realm realm() {
        CustomRealm shiroRealm = new CustomRealm();
        shiroRealm.setCacheManager(ehCacheManager());
        shiroRealm.setCachingEnabled(true);
        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        shiroRealm.setAuthenticationCachingEnabled(true);
        //缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
        shiroRealm.setAuthenticationCacheName("authenticationCache");
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        shiroRealm.setAuthorizationCachingEnabled(true);
        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
        shiroRealm.setAuthorizationCacheName("authorizationCache");
        return shiroRealm;
    }

    /**
     * Shiro的过滤规则
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl(loginUrl);
        filterFactoryBean.setSuccessUrl(successUrl);
        filterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        filterFactoryBean.getFilters().put("AccurateAccessControlFilter", new AccurateAccessControlFilter());
        filterFactoryBean.getFilters().put("myUser", new MyUserFilter());
        filterFactoryBean.getFilters().put("myLogOut", new MyLogOutFilter());

        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        //static开头的url可以匿名访问
//        chain.addPathDefinition("/static/**", "anon");
        //pdf下载页面
        chain.addPathDefinition("/BI/**", "anon");
        chain.addPathDefinition("/downloadPdf", "anon");
        //pdf下载链接
        chain.addPathDefinition("/download", "anon");
        //微信绑定个人信息
        chain.addPathDefinition("/bindUserInfo", "anon");
        //微信认证相关接口
        chain.addPathDefinition("/wx", "anon");
        chain.addPathDefinition("/authorize", "anon");
        chain.addPathDefinition("/userInfo", "anon");
        chain.addPathDefinition("/register", "anon");
        chain.addPathDefinition("/sendCaptcha", "anon");
        chain.addPathDefinition("/jweixin-1.4.0.js", "anon");
        chain.addPathDefinition("/"+verifyFile, "anon");
        //微信支付js
        chain.addPathDefinition("/WxPay/**", "anon");
        //统一下单回调
        chain.addPathDefinition("/weChatPay/unifiedNotify", "anon");
        //退款回调
        chain.addPathDefinition("/weChatPay/refundNotify", "anon");


        chain.addPathDefinition("/file/**", "anon");
        chain.addPathDefinition("/assets/**", "anon");
        chain.addPathDefinition("/admin/ajax/login", "anon");
        chain.addPathDefinition("/admin/user/logout", "myLogOut");
        chain.addPathDefinition("/admin/user/login", "anon");
        chain.addPathDefinition("/admin/**", "AccurateAccessControlFilter");
        //记住我配置
        chain.addPathDefinition("/**", "myUser");
        // 除了以上的请求外，其它请求都需要登录
        // chain.addPathDefinition("/**", "authc");
        filterFactoryBean.setFilterChainDefinitionMap(chain.getFilterChainMap());

        return filterFactoryBean;
    }

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //记住我cookie生效时间30天 ,单位秒;
        simpleCookie.setMaxAge(2592000);
        simpleCookie.setHttpOnly(true);
//         simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /**
     * FormAuthenticationFilter 过滤器 过滤记住我
     *
     * @return
     */
    @Bean
    public FormAuthenticationFilter formAuthenticationFilter() {
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        //对应前端的checkbox的name = rememberMe
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        return formAuthenticationFilter;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入缓存管理器
        securityManager.setCacheManager(ehCacheManager());
        //注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        //设置realm
        securityManager.setRealm(realm());
        //注入session管理器
//         securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 缓存管理器
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(rememberMeCookie());
        return sessionManager;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        //creator.setUsePrefix(true);
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 让某个实例的某个方法的返回值注入为Bean的实例
     * Spring静态注入
     *
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean() {
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(defaultWebSecurityManager());
        return factoryBean;
    }
}
