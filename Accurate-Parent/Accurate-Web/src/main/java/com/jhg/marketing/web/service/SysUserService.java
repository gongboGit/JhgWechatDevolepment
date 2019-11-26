package com.jhg.marketing.web.service;

import com.jhg.marketing.dao.domin.SysUser;
import com.jhg.marketing.dao.domin.UserRole;
import com.jhg.marketing.dao.domin.WeChatUser;
import com.jhg.marketing.dao.domin.WeChatUserDiseasesType;
import com.jhg.marketing.dao.mapper.SysUserMapper;
import com.jhg.marketing.dao.mapper.UserRoleMapper;
import com.jhg.marketing.dao.mapper.WeChatUserDiseasesTypeMapper;
import com.jhg.marketing.dao.mapper.WeChatUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author lxl
 * @since 2019/4/16 13:11
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private WeChatUserMapper weChatUserMapper;
    @Autowired
    private WeChatUserDiseasesTypeMapper weChatUserDiseasesTypeMapper;
    @Value("${user.default.password}")
    private String userDefaultPwd;


    @Transactional(rollbackFor = Exception.class)
    public boolean insertSysUser(SysUser sysUser) {
//        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setRegisterTime(new Date());
        sysUser.setPassword(userDefaultPwd);
        sysUser.setLastLoginTime(new Date());
        sysUser.setType(1);
        sysUserMapper.insert(sysUser);

        //新增用户角色信息
        List<UserRole> userRoleList = new ArrayList<>();
        Integer[] roleIdArr = sysUser.getRoleIdArr();
        for (Integer roleId : roleIdArr) {
            UserRole userRole = new UserRole();
//            userRole.setId(UUID.randomUUID().toString());
            userRole.setRoleId(roleId);
            userRole.setUserId(sysUser.getId());
            userRoleList.add(userRole);
        }
        userRoleMapper.insertUserRole(userRoleList);

        weChatUserMapper.insert(new WeChatUser() {{
            setEnable(false);
            setTrueName(sysUser.getTrueName());
            setUserId(sysUser.getId());
            setMainFlag(1);
        }});
        return true;
    }

    public boolean deleteSysUser(String sysUserIds) {
        List<String> sysUserList = Arrays.asList(sysUserIds.split(","));
        sysUserMapper.deleteBySysUserIdList(sysUserList);
        //TODO 删除用户权限等其他信息
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateSysUser(SysUser sysUser) {
        //重新设置用户角色关系信息
        Integer sysUserId = sysUser.getId();
        Integer[] roleIdArr = sysUser.getRoleIdArr();
        UserRole userRoleParam = new UserRole();
        userRoleParam.setUserId(sysUserId);
        userRoleMapper.delete(userRoleParam);
        if (roleIdArr != null) {
            List<UserRole> userRoleList = new ArrayList<>();
            for (Integer roleId : roleIdArr) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(sysUserId);
                userRoleList.add(userRole);
            }
            userRoleMapper.insertUserRole(userRoleList);
        }

        //重新设置微信用户病种关系
        Integer[] diseasesTypeIds = sysUser.getDiseasesTypeIds();
        List<WeChatUser> select = weChatUserMapper.select(new WeChatUser() {{
            setUserId(sysUserId);
            setMainFlag(1);
        }});
        Integer weChatUserId = select.get(0).getId();
        weChatUserDiseasesTypeMapper.delete(new WeChatUserDiseasesType() {{
            setWeChatUserId(weChatUserId);
        }});
        if (diseasesTypeIds != null) {
            ArrayList<WeChatUserDiseasesType> weChatUserDiseasesTypes = new ArrayList<>();
            for (Integer diseasesTypeId : diseasesTypeIds) {
                weChatUserDiseasesTypes.add(new WeChatUserDiseasesType() {{
                    setDiseasesTypeId(diseasesTypeId);
                    setWeChatUserId(weChatUserId);
                }});
            }
            weChatUserDiseasesTypeMapper.insertWeChatUserDiseasesType(weChatUserDiseasesTypes);
        }
        //TODO 修改WeChat_user
        return sysUserMapper.updateByPrimaryKeySelective(sysUser) == 1;
    }

    public List<SysUser> listUser(SysUser user) {
//        PageHelper.startPage(pageIndex, pageSize);
        List<SysUser> userList = sysUserMapper.listUser(user);
//        userList = userList.stream().peek(x -> {
//                    x.setSysRoleStr(userRoleMapper.getRoleStr(x.getId()));
//                    x.setDiseasesTypeStr(weChatUserDiseasesTypeMapper.getDiseasesTypeStr(x.getWeChatUserId()));
//                }
//
//        ).collect(Collectors.toList());
        return userList;
    }

    public SysUser getSysUserInfo(Integer sysUserId) {
        SysUser sysUser = sysUserMapper.getSysUserInfo(sysUserId);
        sysUser.setSysRoleStr(userRoleMapper.getRoleStr(sysUserId));
        List<Integer> roleIdList = userRoleMapper.getRoleIdArr(sysUserId);
        Integer[] strings = new Integer[roleIdList.size()];
        sysUser.setRoleIdArr(roleIdList.toArray(strings));
        Integer weChatUserId = sysUser.getWeChatUserId();
        sysUser.setDiseasesTypeStr(weChatUserDiseasesTypeMapper.getDiseasesTypeStr(weChatUserId));
        List<Integer> diseasesTypeIdArr = weChatUserDiseasesTypeMapper.getDiseasesTypeIdArr(weChatUserId);
        Integer[] integers = new Integer[diseasesTypeIdArr.size()];
        sysUser.setDiseasesTypeIds(diseasesTypeIdArr.toArray(integers));
        return sysUser;
    }

}
