package com.jhg.marketing.dao.domin.message;

import lombok.Data;

/**
 * 回复消息的基类
 *
 * @author lxl
 */
@Data
public class BaseMessage {

	/**
	 * 开发者微信号
	 */
	protected String ToUserName;

	/**
	 * 发送方账号
	 */
	protected String FromUserName;

	/**
	 * 消息创建时间
	 */
	protected long CreateTime;

	/**
	 * 消息类型
	 */
	protected String MsgType;

	/**
	 * 消息id，64位整型
	 */
	private Integer MsgId;
 
}