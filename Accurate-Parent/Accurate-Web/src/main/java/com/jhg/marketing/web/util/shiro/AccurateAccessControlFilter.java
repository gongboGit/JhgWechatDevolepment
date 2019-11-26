package com.jhg.marketing.web.util.shiro;

import com.jhg.marketing.dao.domin.Permission;
import com.jhg.marketing.dao.domin.SysUser;
import com.jhg.marketing.dao.domin.WebConfig;
import com.jhg.marketing.dao.mapper.PermissionMapper;
import com.jhg.marketing.dao.mapper.SysUserMapper;
import com.jhg.marketing.web.admin.service.MenuService;
import com.jhg.marketing.web.admin.service.MenuTreeList;
import com.jhg.marketing.web.config.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class AccurateAccessControlFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object obj) throws Exception {
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("==》AccurateAccessControlFilter：访问路径是：" + req.getRequestURI());
        Subject currentUser = SecurityUtils.getSubject();
        //判断用户是通过记住我功能自动登录,此时session失效
        WebConfig webConfig = applicationContext.getBean(WebConfig.class);
        if (!currentUser.isAuthenticated() && currentUser.isRemembered()) {
            try {
                log.info("==》自动登录");
                String username = ((SysUser) currentUser.getPrincipal()).getUsername();
                SysUserMapper userMapper = applicationContext.getBean(SysUserMapper.class);
                SysUser user = userMapper.findByUserName(username);
                user.setRememberMe(currentUser.isRemembered());
                //对密码进行加密后验证
                UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), currentUser.isRemembered());
                //把当前用户放入session
                currentUser.login(token);
                Session session = currentUser.getSession();
                session.setAttribute("user", user);
//                session.setTimeout(24*60*60*1000);
                //设置会话的过期时间--ms,默认是30分钟，设置负数表示永不过期
                //session.setTimeout(-1000l);
            } catch (Exception e) {
                //自动登录失败,跳转到登录页面
                resp.sendRedirect(webConfig.getDomain() + "/" + webConfig.getLoginUrl());
                return true;
            }
            if (!currentUser.isAuthenticated()) {
                //自动登录失败,跳转到登录页面
                resp.sendRedirect(webConfig.getDomain() + "/" + webConfig.getLoginUrl());
                return true;
            }
        }

        if (!currentUser.isAuthenticated() && !currentUser.isRemembered()) {
            log.debug("未登录跳转到登录页面！");
            handleRedirect(req, resp, webConfig.getDomain() + webConfig.getLoginUrl());
//			resp.sendRedirect(webConfig.getDomain() + webConfig.getLoginUrl());
            return true;
        }
        String url = getPathWithinApplication(request);
//		if(excList.contains(url)) {
//			return true;
//		}
//		Subject subject = getSubject(request,response);
//		Message error=AjaxDataController.fail();
//		if(!subject.isAuthenticated()){
//			error.setMsg("当前用户尚未登录");
//			error.setCode(100);
//			request.setAttribute("error", error);
//			return false;
//			return true;
//		}
        PermissionMapper pDao = applicationContext.getBean(PermissionMapper.class);
        List<Permission> perms = pDao.select(new Permission(){{
            setFlag(1);
        }});


        MenuService mService = applicationContext.getBean(MenuService.class);
        MenuTreeList mList = null;
        try {
            mList = mService.getMenuTreeList();
        } catch (Exception e) {
//			e.printStackTrace();
            log.error(e.getMessage());
        }

        request.setAttribute("menuLs", mList);

        Optional<Permission> optPerm = perms.stream().filter(it -> it.getUrl().toLowerCase().equals(url.toLowerCase())).findFirst();
        if (optPerm.isPresent()) {
            Permission p = optPerm.get();
            request.setAttribute("viewTitle", p.getTitle());
            request.setAttribute("menuItem", p);
            while (!p.getIsShow()) {
                p = pDao.selectByPrimaryKey(p.getPid());
            }
            request.setAttribute("curMenuItem", p);
            request.setAttribute("viewLevel", p.getId());
//			if(!subject.isPermitted(optPerm.get().getId()+"")){
//				error.setMsg("当前功能未对您开放："+url);
//				error.setCode(200);
//				request.setAttribute("error", error);
//				return false;
//			}
        }


        return true;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     * onAccessDenied是否执行取决于isAccessAllowed的值，如果返回true则onAccessDenied不会执行；如果返回false，执行onAccessDenied
     * 如果onAccessDenied也返回false，则直接返回，不会进入请求的方法（只有isAccessAllowed和onAccessDenied的情况下）
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

//		String curUrl=getPathWithinApplication(request);
//
//		if(excList.contains(curUrl)) {
//			return true;
//		}
//
//		Message msg=(Message)request.getAttribute("error");
//		if(msg.getCode()==100){
//			((HttpServletResponse)response).sendRedirect(excList.get(0));
//			return true;
//		}
//		request.getRequestDispatcher(excList.get(1)).forward(request, response);
        return true;

    }

    /**
     * 对于请求是ajax请求重定向问题的处理方法
     *
     * @param request
     * @param response
     * @param url
     * @throws IOException
     */
    private void handleRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.setHeader("redirect", url);
            // 注意：
            // 此句代码是为了防止AJAX没有正常响应时触发error方法，
            // 另外这里的输出结果可以是布尔值或数值，如果使用字符串做响应结果，在JSON解析时会出错，还是会进error方法
            response.getWriter().print(true);
        } else {
            response.sendRedirect(url);
        }
    }

}
