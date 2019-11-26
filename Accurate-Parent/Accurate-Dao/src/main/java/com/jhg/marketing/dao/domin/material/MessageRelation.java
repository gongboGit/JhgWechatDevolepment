package com.jhg.marketing.dao.domin.material;

import com.jhg.marketing.common.Explain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 基础消息
 */
@Data
@Table(name="message_relation")
public class MessageRelation implements Serializable {

	@Explain(value = "id", isEdit = false)
	@Id
	@Column(name = "id", insertable = false,updatable = false)
	private Integer id;

	/**
     * 基础消息id
	 */
	@Column(name = "base_id")
	private Integer baseId;

	/**
     * 消息id
	 */
	@Column(name = "message_id")
	private Integer messageId;

	/**
     * 排序
	 */
	private Integer sort;

}
