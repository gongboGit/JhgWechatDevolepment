package com.jhg.marketing.web.admin.controller;

import com.alibaba.fastjson.JSON;
import com.jhg.marketing.common.DataRow;
import com.jhg.marketing.common.DataTable;
import com.jhg.marketing.dao.domin.Permission;
import com.jhg.marketing.dao.domin.SysRole;
import com.jhg.marketing.dao.domin.SysUser;
import com.jhg.marketing.dao.mapper.PermissionMapper;
import com.jhg.marketing.web.admin.service.MenuService;
import com.jhg.marketing.web.controller.BaseController;
import com.jhg.marketing.web.service.PermissionService;
import com.jhg.marketing.web.service.SysRoleService;
import com.jhg.marketing.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.util.StringUtil;

@Controller
@RequestMapping("admin/system")
public class AdminSystemSettingController extends BaseController {

    @Autowired
    PermissionMapper pDao;
    @Autowired
    MenuService mService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 权限显示
     *
     * @param roleid
     * @param model
     * @return
     */
    @GetMapping("perms")
    public ModelAndView perms(Integer roleid, Model model) {
//        TreeList<TreeNode<Permission>> treeList = mService.getTreeList(roleid);
//        model.addAttribute("treeNodeList", treeList);
        model.addAttribute("roles", sysRoleService.listRoles());
        model.addAttribute("roleId", roleid);
        return view();
    }

    /**
     * 权限编辑页面
     *
     * @param id
     * @return
     */
    @GetMapping("editperm")
    public DataRow editPerm(@RequestParam(value = "id", defaultValue = "") String id) {
        if (StringUtil.isEmpty(id)) {
            Permission item = new Permission();
            item.setIsShow(true);
            item.setEnabled(true);
            System.out.println(JSON.toJSONString(new DataRow<Permission>(item)));
            return new DataRow<Permission>(item);
        }

        return new DataRow<Permission>(pDao.selectByPrimaryKey(id));
    }

    /**
     * 编辑菜单
     *
     * @param p
     * @return
     */
    @PostMapping("postperm")
    public String postPerm(Permission p) {
        if (p.getEnabled() == null) {
            p.setEnabled(false);
        }
        if (p.getIsShow() == null) {
            p.setIsShow(false);
        }
        Integer permissionId = p.getId();
        Permission permission = permissionService.getPermissionInfo(permissionId);
        if (permission != null) {
            //修改
            permissionService.updatePermission(p);
        } else {
            //新增
            permissionService.insertPermission(p);
        }
        return "redirect:perms";
    }

    /**
     * 用户管理页面
     *
     * @param model
     * @return
     */
    @RequestMapping("sysuser")
    public ModelAndView sysUser(Model model, SysUser user,
                                @RequestParam(value = "pageindex", defaultValue = "1") int pageIndex,
                                @RequestParam(value = "pagesize", defaultValue = "10") int pageSize) {

        model.addAttribute("roles", sysRoleService.listRoles());
//        List<SysUser> users = sysUserService.listUser(user, pageIndex, pageSize);
//        model.addAttribute("pageInfo", new PageInfo<>(users));
//        model.addAttribute("table", new DataTable<>(users, SysUser.class));
        return view();
    }

    /**
     * 编辑用户页面
     *
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping("editsysuser")
    public ModelAndView editSysUser(Model model, @RequestParam(value = "id", required = false) Integer userId) {

        SysUser sysUser;
        if (userId != null) {
            //修改用户
            sysUser = sysUserService.getSysUserInfo(userId);
        } else {
            //新增用户
            sysUser = new SysUser();
            sysUser.setEnabled(true);
        }
        model.addAttribute("roles", sysRoleService.listRoles());
        model.addAttribute("sysUser", sysUser);
        return view();
    }

    /**
     * 角色管理页面
     *
     * @param model
     * @return
     */
    @RequestMapping("role")
    public ModelAndView role(Model model) {
        model.addAttribute("table", new DataTable<>(sysRoleService.listRole(), SysRole.class));
        return view();
    }

    /**
     * 编辑角色页面
     *
     * @param id
     * @return
     */
    @RequestMapping("editrole")
    public DataRow editRole(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null) {
            //新增
            SysRole sysRole = new SysRole();
            sysRole.setEnabled(true);

            return new DataRow<SysRole>(sysRole);
        }
        //修改
        return new DataRow<SysRole>(sysRoleService.getSysRoleById(id));
    }

    /**
     * 编辑角色
     *
     * @param sysRole
     * @return
     */
    @RequestMapping("postrole")
    public String postRole(SysRole sysRole) {
        Integer roleId = sysRole.getId();
        if (roleId != null) {
            //修改
            sysRoleService.updateSysRole(sysRole);
        } else {
            //新增
            sysRoleService.insertSysRole(sysRole);
        }
        return "redirect:role";
    }

    /**
     * 跳转到病种管理页面
     *
     * @return
     */
    @RequestMapping("toDiseasesTypeUI")
    public String toDiseasesTypeUI(Model model) {
        return "admin/system/diseasesType";
    }

}
