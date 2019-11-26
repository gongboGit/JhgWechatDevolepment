package com.jhg.marketing.dao.domin;

import com.jhg.marketing.common.Explain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name="hospital_introduction")
public class HospitalIntroduction implements Serializable {

	@Explain(value = "id", isEdit = false)
	@Id
	@Column(name = "id", insertable = false,updatable = false)
	private Integer id;

//	@Explain(value = "标题")
//	private String title;

	@Explain(value = "内容")
	private String content;

	/**
	 * 1是医院介绍，2是轮播图
	 */
	@Explain(value = "类型")
	private String type;

//	@Explain(value = "图片")
//	private String picture;

//	@Explain(value = "顺序")
//	private Integer sort;
	
}
