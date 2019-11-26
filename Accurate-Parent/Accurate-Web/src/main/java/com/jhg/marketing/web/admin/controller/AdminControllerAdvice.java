package com.jhg.marketing.web.admin.controller;

import com.jhg.marketing.dao.domin.Permission;
import com.jhg.marketing.dao.mapper.PermissionMapper;
import com.jhg.marketing.web.admin.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice(basePackages = {"com.jhg.marketing.web.admin"})
public class AdminControllerAdvice {


    @Autowired
    MenuService mService;
    @Autowired
    PermissionMapper pDao;

//	@ModelAttribute
//	public void menuls(Model model,HttpServletRequest request){
////		String uri = request.getRequestURI();
//		log.info("--advice");
////		MenuTreeList mList=mService.getMenuTreeList();
////		model.addAttribute("menuLs", mList);
//		List<Permission> perms=pDao.selectAll();
//		String url=request.getRequestURI();
//		Optional<Permission> optPerm=perms.stream().filter(it->it.getUrl().toLowerCase().equals(url.toLowerCase())).findFirst();
//		if(optPerm.isPresent()){
//			Permission permission=optPerm.get();
//			model.addAttribute("curMenuItem", permission);
//			model.addAttribute("viewTitle", permission.getTitle());
//			model.addAttribute("viewLevel", permission.getId());
////			if(!subject.isPermitted(optPerm.get().getId())){
////				error.setMsg("当前功能未对您开放："+url);
////				error.setCode(200);
////				request.setAttribute("error", error);
////				return false;
////			}
//		}
//	}


    /**
     * 设置后台页面面包屑
     *
     * @param model
     * @param menuItem 当前页面对应的权限（从AccurateAccessControlFilter设置）
     */
    @ModelAttribute
    public void siteMap(Model model, @RequestAttribute(value = "menuItem", required = false) Permission menuItem) {
        if (menuItem == null) {
            return;
        }
        //1.把Level 001002001拆分成001、001002、001002001

//        List<String> ls = IntStream.
//                range(1, menuItem.getId().length() / MenuService.STEP + 1)
//                .mapToObj(it -> menuItem.getId().substring(0, it * MenuService.STEP))
//                .collect(Collectors.toList());

        //2.获取对应的项
        List<Permission> menuItems = pDao.selectAll();
        Integer pid = menuItem.getPid();
        LinkedList<Permission> permissionList = new LinkedList<>();
        permissionList.add(menuItem);
        while (pid != 0) {
            Integer finalPid = pid;
            Permission permission = menuItems.stream().filter(x -> x.getId().equals(finalPid)).collect(Collectors.toList()).get(0);
            permissionList.add(0, permission);
            pid = permission.getPid();
        }
//        menuItems = menuItems.stream().filter(it -> ls.contains(it.getId())).collect(Collectors.toList());
//        menuItems.sort((a, b) -> a.getId().compareTo(b.getId()));
        model.addAttribute("sitemap", permissionList);
    }
}
