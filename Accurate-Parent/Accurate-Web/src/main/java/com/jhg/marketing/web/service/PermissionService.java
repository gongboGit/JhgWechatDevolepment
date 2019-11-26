package com.jhg.marketing.web.service;

import com.jhg.marketing.dao.domin.Permission;
import com.jhg.marketing.dao.mapper.PermissionMapper;
import com.jhg.marketing.dao.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author lxl
 * @since 2019/4/15 14:51
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public boolean insertPermission(Permission permission) {
        return permissionMapper.insert(permission) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deletePermission(String permissionIds) {
        List<String> permIdList = Arrays.asList(permissionIds.split(","));
        //删除角色权限信息
        rolePermissionMapper.deleteByPermissonIds(permIdList);
        permissionMapper.deleteByPermissonIds(permIdList);
        return true;
    }

    public boolean updatePermission(Permission permission) {
        return permissionMapper.updateByPrimaryKeySelective(permission) == 1;
    }

    public List<Permission> listPermission() {
        return permissionMapper.selectAll();
    }

    public Permission getPermissionInfo(Integer permissionId) {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }
}
