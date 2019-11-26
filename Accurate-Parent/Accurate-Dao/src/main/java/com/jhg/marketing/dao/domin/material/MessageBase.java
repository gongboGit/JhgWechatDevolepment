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
@Table(name="message_base")
public class MessageBase implements Serializable {

	@Id
	@Column(name = "id", insertable = false,updatable = false)
	private Integer id;

	/**
     * 消息类型
	 */
	@Column(name = "message_type")
	private String messageType;

	/**
     * media_id
	 */
	@Column(name = "media_id")
	private String mediaId;

	/**
	 * 类型：1是医院介绍
	 */
	private Integer type;

	/**
	 * 是否启用
	 */
	private Boolean enabled;

}
