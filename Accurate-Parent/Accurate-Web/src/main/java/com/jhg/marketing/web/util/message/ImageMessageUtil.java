package com.jhg.marketing.web.util.message;

import com.jhg.marketing.dao.domin.message.Image;
import com.jhg.marketing.dao.domin.message.ImageMessage;
import com.jhg.marketing.web.util.AccessTokenUtil;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Component
public class ImageMessageUtil implements BaseMessageUtil<ImageMessage> {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	/**
	 * 将信息转为xml格式
	 */
	@Override
	public String messageToxml(ImageMessage imageMessage) {
		XStream xStream = new XStream();
		xStream.alias("xml", imageMessage.getClass());
		xStream.alias("Image", Image.class);
		return xStream.toXML(imageMessage);
	}
	/**
	 * 封装信息
	 */
	@Override
	public String initMessage(String FromUserName, String ToUserName) {
		Image image = new Image();
		image.setMediaId(getMediaId());
		ImageMessage message = new ImageMessage();
		message.setFromUserName(ToUserName);
		message.setToUserName(FromUserName);
		message.setCreateTime(System.currentTimeMillis());
		message.setImage(image);
		message.setMsgType("image");
		return messageToxml(message);
	}
	/**
	 * 获取Media
	 * @return
	 */
	public String getMediaId(){
		String path = "D:/file/contract/15532676520540c5-420.png";
//		WeChatUtil weChatUtil = new WeChatUtil();
		String accessToken = accessTokenUtil.getAccessTokenByDataBase();
		String mediaId = null;
		try {
			mediaId = UploadUtil.upload(path, accessToken, "image");
			
		} catch (KeyManagementException | NoSuchAlgorithmException
				| NoSuchProviderException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mediaId;
	}
}