package com.jhg.marketing.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.jhg.marketing.dao.domin.Permission;

@Mapper
public interface PermissionMapper extends tk.mybatis.mapper.common.Mapper<Permission> {
    @Select({
            "SELECT p.* " +
                    "FROM user_role u,role_permission r,permission p " +
                    "WHERE u.role_id = r.role_id " +
                    "AND r.permission_id = p.id " +
                    "and u.user_id =#{userId}"
    })
    List<Permission> findByUserId(Integer userId);

    @Delete({
            "<script>" +
                    "DELETE FROM permission " +
                    "WHERE id IN " +
                    "<foreach collection=\"permIdList\" open=\"(\" close=\")\" separator=\",\" item=\"item\">" +
                    "#{item}" +
                    "</foreach>" +
            "</script>"
    })
    Integer deleteByPermissonIds(@Param("permIdList") List<String> permIdList);

    @Select({
            "<script>" +
                    "SELECT p.*,checked = CASE " +
                    "WHEN rp.id is null THEN 'false' " +
                    "ELSE 'true' " +
                    "END " +
                    "FROM permission p " +
                    "LEFT JOIN role_permission rp " +
                    "ON p.id = rp.permission_id " +
                    "<if test=\"roleId != null\">" +
                        "AND rp.role_id = #{roleId}" +
                    "</if>" +
                    "<if test=\"roleId == null\">" +
                        "AND rp.role_id = ''" +
                    "</if>" +
            "</script>"
    })
    List<Permission> listPermissionByRoleId(@Param("roleId") Integer roleId);

    @Select({
            "SELECT p.* " +
                    "FROM sys_user su, user_role ur,role_permission rp, permission p " +
                    "WHERE su.id = ur.user_id " +
                    "AND ur.role_id = rp.role_id " +
                    "AND rp.permission_id = p.id " +
                    "AND su.id = #{userId} " +
                    "AND p.flag = 1 " +
                    "AND p.is_show = 1"
    })
    List<Permission> listPermissionByUserIdWithBackMenu(Integer userId);

    @Select({
            "SELECT p.* " +
                    "FROM permission p " +
                    "WHERE p.flag = 1 " +
                    "AND p.is_show = 1"
    })
    List<Permission> listPermissionWithBackMenu();

}
