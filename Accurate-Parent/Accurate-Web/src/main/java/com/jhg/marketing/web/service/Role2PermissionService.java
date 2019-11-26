package com.jhg.marketing.web.service;

import com.jhg.marketing.dao.domin.RolePermission;
import com.jhg.marketing.dao.domin.SysRole2MenuWithRoleId;
import com.jhg.marketing.dao.mapper.RolePermissionMapper;
import com.jhg.marketing.web.util.shiro.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lxl
 * @since 2019/4/15 10:12
 */
@Service
public class Role2PermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    public List<RolePermission> getPermsByRoleId(Integer roleId) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        return rolePermissionMapper.select(rolePermission);
    }

    public boolean insertRole2Permission(RolePermission rolePermission) {
//        rolePermission.setId(UUID.randomUUID().toString());
        return rolePermissionMapper.insert(rolePermission) == 1;
    }

    public boolean deleteRole2Permission(String role2PermissionId) {
        return rolePermissionMapper.deleteByPrimaryKey(role2PermissionId) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole2Perms(SysRole2MenuWithRoleId sysRole2MenuWithRoleId) {
        Integer roleId = sysRole2MenuWithRoleId.getRoleId();
        if (roleId != null) {
            //删除当前角色所有权限
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermissionMapper.delete(rolePermission);
            //新增
            List<RolePermission> rolePermissionList = sysRole2MenuWithRoleId.getLs();
            if (rolePermissionList.size() != 0) {
                rolePermissionMapper.insertRole2Perms(rolePermissionList);
            }

            //添加成功之后 清除缓存
            DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
            CustomRealm shiroRealm = (CustomRealm) securityManager.getRealms().iterator().next();
            //清除权限 相关的缓存
            shiroRealm.clearAllCache();
        }
        return true;
    }
}
