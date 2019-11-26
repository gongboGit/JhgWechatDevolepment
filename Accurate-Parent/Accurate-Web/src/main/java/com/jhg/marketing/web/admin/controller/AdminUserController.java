package com.jhg.marketing.web.admin.controller;

import com.jhg.marketing.web.controller.BaseController;
import com.jhg.marketing.web.util.shiro.CustomRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class AdminUserController extends BaseController {

    @GetMapping(path = {"admin/user/login", "", "/"})
    public String login() {
        return "admin/user/login";
    }

    @GetMapping("admin/user/logout")
//    @CacheEvict(cacheNames = {"authorizationCache","authenticationCache"}, allEntries = true)
    public boolean logout() {
        log.debug("------注销账号！");
        //添加成功之后 清除缓存
//        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
//        CustomRealm shiroRealm = (CustomRealm) securityManager.getRealms().iterator().next();
        //清除权限 相关的缓存
//        shiroRealm.clearAllCache();
//        session.invalidate();
        return true;
    }

    @RequestMapping("admin/user/updatePwd")
    public ModelAndView updatePwd() {
        return view();
    }
}
