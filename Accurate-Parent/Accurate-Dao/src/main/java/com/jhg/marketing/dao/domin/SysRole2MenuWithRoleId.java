package com.jhg.marketing.dao.domin;

import lombok.Data;

import java.util.List;

@Data
public class SysRole2MenuWithRoleId {
	private Integer roleId;
	private List<RolePermission> ls;

}