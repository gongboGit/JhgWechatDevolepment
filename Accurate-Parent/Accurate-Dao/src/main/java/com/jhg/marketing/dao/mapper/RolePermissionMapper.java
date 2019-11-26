package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.RolePermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends tk.mybatis.mapper.common.Mapper<RolePermission> {

    @Delete({
            "<script>" +
                    "DELETE FROM role_permission " +
                    "WHERE role_id IN " +
                    "<foreach collection=\"sysRoleIdList\" open=\"(\" close=\")\" separator=\",\" item=\"item\">" +
                    "#{item}" +
                    "</foreach>" +
            "</script>"
    })
    Integer deleteBySysRoleIds(@Param("sysRoleIdList") List<String> sysRoleIdList);

    @Insert({
            "<script>" +
                    "INSERT INTO role_permission " +
                    "VALUES " +
                    "<foreach collection=\"ls\" separator=\",\" item=\"item\">" +
                    "(#{item.roleId},#{item.permissionId})" +
                    "</foreach>" +
            "</script>"
    })
    Integer insertRole2Perms(@Param("ls") List<RolePermission> ls);

    @Delete({
            "<script>" +
                    "DELETE FROM role_permission " +
                    "WHERE permission_id IN " +
                    "<foreach collection=\"permIdList\" open=\"(\" close=\")\" separator=\",\" item=\"item\">" +
                    "#{item}" +
                    "</foreach>" +
            "</script>"
    })
    Integer deleteByPermissonIds(@Param("permIdList") List<String> permIdList);

    @Select({
            "<script>" +
                    "SELECT p.id " +
                    "FROM role_permission rp,permission p " +
                    "WHERE rp.permission_id = p.id " +
                    "AND rp.role_id " +
                    "IN " +
                    "<foreach collection=\"roleList\" open=\"(\" close=\")\" separator=\",\" item=\"item\"> " +
                    "#{item} " +
                    "</foreach>" +
            "</script>"
    })
    List<String> listPermissionByRoleIdList(@Param("roleList") List<String> roleList);
}
