package com.jhg.marketing.web.admin.service;

import com.jhg.marketing.dao.domin.Permission;
import com.jhg.marketing.dao.mapper.PermissionMapper;
import com.jhg.marketing.web.util.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    //    public final static int STEP = 3;
    @Autowired
    PermissionMapper pDao;

    public MenuTreeList getMenuTreeList() {

        List<Permission> ls = pDao.listPermissionByUserIdWithBackMenu(ApplicationUtil.getSessionUserId());
//        List<Permission> ls = pDao.listPermissionWithBackMenu();
//        ls = ls.stream().filter(Permission::getIsShow).collect(Collectors.toList());
        return generTreeList(0, ls);
    }

//    public TreeList<TreeNode<Permission>> getTreeList(Integer roleId) {
//        List<Permission> ls = pDao.listPermissionByRoleId(roleId);
////        ls = ls.stream().peek(x -> {
////            if ("".equals(roleId)) {
////                x.setChecked("false");
////            }
////        }).collect(Collectors.toList());
//
//        FuncTwo<Permission, String> thunk = it -> it.getId();
//        TreeList<TreeNode<Permission>> treeList = TreeUtil.generTreeList(null, ls, thunk, 3);
//        return treeList;
//    }

    public MenuTreeList generTreeList(Integer plevel, List<? extends Permission> menuList) {

        MenuTreeList treeLs = new MenuTreeList();
//        String lev = plevel == null ? "" : plevel;
//        int takeSize = lev.length() + STEP;

//        Predicate<Permission> filter = (it) -> it.getId().length() == takeSize && it.getId().startsWith(lev);
//        if (lev.length() == 0) {
//            filter = it -> it.getId().length() == takeSize;
//        }

        List<Permission> ls = menuList.stream().filter(x -> x.getPid().equals(plevel)).sorted(Comparator.comparing(Permission::getId)).collect(Collectors.toList());

        for (Permission menuItem : ls) {
            // menuItem.setPlevel(plevel);
            MenuNode node = new MenuNode(menuItem);
            treeLs.add(node);
            node.setChildren(generTreeList(menuItem.getId(), menuList));
        }

        return treeLs;
    }
}
