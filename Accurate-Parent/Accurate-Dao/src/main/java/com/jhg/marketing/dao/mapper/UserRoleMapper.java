package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.SysRole;
import com.jhg.marketing.dao.domin.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRoleMapper extends tk.mybatis.mapper.common.Mapper<UserRole> {

	@Select({
			"select r.id,r.name " +
					"from user_role ur,sys_role r " +
					"where ur.role_id = r.id " +
					"and ur.user_id = #{sysUserId}"
	})
	List<SysRole> listRoleByUserId(String sysUserId);

	@Select({
			"SELECT stuff((" +
					"SELECT ',' + r.name " +
					"FROM user_role ur,sys_role r " +
					"where ur.role_id = r.id " +
					"and ur.user_id = #{sysUserId} " +
					"for xml path('')" +
					"),1,1,'')"
	})
	String getRoleStr(Integer sysUserId);

	@Select({
			"SELECT role_id " +
					"FROM user_role " +
					"WHERE user_id = #{sysUserId}"
	})
	List<Integer> getRoleIdArr(Integer sysUserId);

	@Insert({
			"<script>" +
			"INSERT INTO user_role " +
					"VALUES " +
					"<foreach collection=\"userRoleList\" separator=\",\" item=\"item\">" +
					"	(#{item.roleId},#{item.userId})" +
					"</foreach>" +
			"</script>"
	})
	Integer insertUserRole(@Param("userRoleList") List<UserRole> userRoleList);
}
