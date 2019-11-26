package com.jhg.marketing.web.util.message;

/**
 * 消息封装工具类的基类,这里采用泛型,方便后期功能扩展
 *
 * @author lxl
 */
public interface BaseMessageUtil <T>{
	/**
	 * 将回复的信息对象转xml格式给微信
	 * @param t
	 * @return
	 */
	public  abstract  String messageToxml(T t);
	
	/**
	 * 回复的信息封装
	 * @param FromUserName
	 * @param ToUserName
	 * @return
	 */
	public abstract  String initMessage(String FromUserName, String ToUserName);
}