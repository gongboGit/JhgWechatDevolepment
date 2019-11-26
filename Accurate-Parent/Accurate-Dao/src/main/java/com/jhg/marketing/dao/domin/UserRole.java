package com.jhg.marketing.dao.domin;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.util.List;

@Data
@Table(name="user_role")
public class UserRole {
	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Integer id;

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "user_id")
	private Integer userId;
}
