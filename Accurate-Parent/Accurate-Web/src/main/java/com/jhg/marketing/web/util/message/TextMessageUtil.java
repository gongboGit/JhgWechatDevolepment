package com.jhg.marketing.web.util.message;

import com.jhg.marketing.dao.domin.message.TextMessage;
import com.thoughtworks.xstream.XStream;

/**
 * 文本消息封装成xml
 *
 * @author lxl
 */
public class TextMessageUtil{

	/**
	 * 将发送消息封装成对应的xml格式
	 */
	public  String messageToxml(TextMessage message) {
		XStream xstream  = new XStream();
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}

	/**
	 * 封装发送消息对象,封装时，需要将调换发送者和接收者的关系
	 *
	 * @param FromUserName
	 * @param ToUserName
	 */
	public  String initMessage(String FromUserName, String ToUserName) {
		TextMessage text = new TextMessage();
		text.setToUserName(FromUserName);
		text.setFromUserName(ToUserName);
		text.setContent("欢迎关注<所谓杂货铺>");
		text.setCreateTime(System.currentTimeMillis());
		text.setMsgType("text");
		return  messageToxml(text);
	}

	/**
	 * 添加封装发送消息的方法，重载，将内容传入
	 *
	 * @param FromUserName
	 * @param ToUserName
	 * @param Content
	 * @return
	 */
	public String initMessage(String FromUserName, String ToUserName,String Content) {
		TextMessage text = new TextMessage();
		text.setToUserName(FromUserName);
		text.setFromUserName(ToUserName);
		text.setContent(Content);
		text.setCreateTime(System.currentTimeMillis());
		text.setMsgType("text");
		return  messageToxml(text);
	}

}