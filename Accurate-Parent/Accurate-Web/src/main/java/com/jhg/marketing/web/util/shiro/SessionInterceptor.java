//package com.jhg.marketing.web.util.shiro;
//
//import com.jhg.marketing.dao.domin.SysUser;
//import com.jhg.marketing.dao.mapper.SysUserMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * session拦截器，如果是自动登录，重新设置session
// */
//@Slf4j
//public class SessionInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private SysUserMapper userMapper;
//    @Value("${shiro.loginUrl}")
//    private String loginUrl;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        Subject currentUser = SecurityUtils.getSubject();
//        //判断用户是通过记住我功能自动登录,此时session失效
//        if(!currentUser.isAuthenticated() && currentUser.isRemembered()){
//            try {
//                log.info("---SessionInterceptor");
//                String username = ((SysUser) currentUser.getPrincipal()).getUsername();
//                SysUser user = userMapper.findByUserName(username);
//                user.setRememberMe(currentUser.isRemembered());
//                //对密码进行加密后验证
//                UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(),currentUser.isRemembered());
//                //把当前用户放入session
//                currentUser.login(token);
//                Session session = currentUser.getSession();
//                session.setAttribute("user",user);
////                session.setTimeout(24*60*60*1000);
//                //设置会话的过期时间--ms,默认是30分钟，设置负数表示永不过期
//                //session.setTimeout(-1000l);
//            }catch (Exception e){
//                //自动登录失败,跳转到登录页面
//                response.sendRedirect(request.getContextPath()+loginUrl);
//                return false;
//            }
//            if(!currentUser.isAuthenticated()){
//                //System.out.println(currentUser.isAuthenticated());
//                //自动登录失败,跳转到登录页面
//                response.sendRedirect(request.getContextPath()+loginUrl);
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//    }
//}