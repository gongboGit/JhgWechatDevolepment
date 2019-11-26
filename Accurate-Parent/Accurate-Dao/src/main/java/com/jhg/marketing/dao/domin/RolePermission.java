package com.jhg.marketing.dao.domin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="role_permission")
public class RolePermission {
	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Integer id;

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "permission_id")
	private Integer permissionId;
}
