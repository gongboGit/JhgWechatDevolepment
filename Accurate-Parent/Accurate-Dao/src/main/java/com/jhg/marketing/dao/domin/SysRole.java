package com.jhg.marketing.dao.domin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jhg.marketing.common.Explain;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Table(name="sys_role")
public class SysRole implements Serializable {

	@Explain(value = "ID", isEdit = false)
	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Integer id;

	@Explain(value = "角色名称", required = true)
	@Column(name="name")
	private String name;

	@Explain(value = "是否启用")
	@Column(name="enabled")
	private Boolean enabled;

	@Explain(value = "创建时间")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="create_time")
	private Date createTime;

	@Explain(value = "备注", dataType = "textarea")
	private String remark;
}
