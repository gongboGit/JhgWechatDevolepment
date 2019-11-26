package com.jhg.marketing.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.jhg.marketing.dao.domin.SysRole;

@Mapper
public interface SysRoleMapper  extends tk.mybatis.mapper.common.Mapper<SysRole> {

	@Select({
		"select a.* from sys_role a left join user_role b on a.id=b.user_id where b.user_id=#{userId}"
	})
	List<SysRole> findByUserId(Integer userId);

	@Select({
			"select * from sys_role where enabled = 1"
	})
	List<SysRole> listRoles();

	@Delete({
			"<script>" +
					"DELETE FROM sys_role " +
					"WHERE id IN " +
					"<foreach collection=\"sysRoleIdList\" open=\"(\" close=\")\" separator=\",\" item=\"item\">" +
					"#{item}" +
					"</foreach>" +
			"</script>"
	})
	Integer deleteBySysRoleIds(@Param("sysRoleIdList") List<String> sysRoleIdList);

}
