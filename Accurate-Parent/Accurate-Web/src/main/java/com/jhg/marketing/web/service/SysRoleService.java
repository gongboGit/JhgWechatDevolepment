package com.jhg.marketing.web.service;

import com.jhg.marketing.dao.domin.SysRole;
import com.jhg.marketing.dao.mapper.RolePermissionMapper;
import com.jhg.marketing.dao.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@Service
public class SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public List<SysRole> listRoles() {
        return sysRoleMapper.listRoles();
    }

    public boolean insertSysRole(SysRole sysRole) {
//        sysRole.setId(UUID.randomUUID().toString());
        return sysRoleMapper.insert(sysRole) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteSysRole(String sysRoleIds) {
        List<String> sysRoleIdList = Arrays.asList(sysRoleIds.split(","));
        //删除角色权限信息
        rolePermissionMapper.deleteBySysRoleIds(sysRoleIdList);
        return sysRoleMapper.deleteBySysRoleIds(sysRoleIdList) == 1;
    }

    public boolean updateSysRole(SysRole sysRole) {
        return sysRoleMapper.updateByPrimaryKeySelective(sysRole) == 1;
    }

    public SysRole getSysRoleById(Integer id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    public List<SysRole> listRole() {
        return sysRoleMapper.selectAll();
    }
}
