package com.jhg.marketing.web.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.exception.WxErrorException;
import com.jhg.marketing.web.util.material.MaterialUtil;
import com.jhg.marketing.web.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 素材管理接口
 *
 * @author lxl
 * @since 5/28/2019 4:09 PM
 */
@RestController
@RequestMapping("admin/message")
public class MessageController {

    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private MaterialUtil materialUtil;

    /**
     * 根据openId群发消息
     *
     * @param msg
     * @return
     */
    @RequestMapping("massSendByOpenId")
    public Message massSendByOpenId(String msg) {
        Map map = JSONObject.parseObject(msg, Map.class);
        String msgtype = ((String) map.get("msgtype"));
        // 如果是图文消息或者视频，重新获取media_id
        if ("mpnews".equals(msgtype)) {
            Map mpnews = (Map) map.get("mpnews");
            String mediaId = mpnews.get("media_id").toString();
            try {
                JSONObject material = materialUtil.getMaterial(mediaId);
                Object news_item = material.get("news_item");
                Map articles = new HashMap<String, Object>(1) {
                    {
                        put("articles", news_item);
                    }
                };
                JSONObject jsonObject = messageUtil.massSendUploadNews(JSON.toJSONString(articles));
                Object media_id = jsonObject.get("media_id");
                mpnews.put("media_id", media_id);
                map.put("mpnews", mpnews);
            } catch (WxErrorException e) {
                e.printStackTrace();
                return Message.fail(e.getError().getErrorMsg());
            }
        } else if ("mpvideo".equals(msgtype)) {
            Map mpvideo = (Map) map.get("mpvideo");
            String mediaId = mpvideo.get("media_id").toString();
            String title = mpvideo.get("title").toString();
            String description = mpvideo.get("description").toString();
            try {
                JSONObject jsonObject = messageUtil.massSendUploadVideo(mediaId, title, description);
                String media_id = jsonObject.getString("media_id");
                mpvideo.put("media_id", media_id);
                map.put("mpvideo", mpvideo);
            } catch (WxErrorException e) {
                e.printStackTrace();
                return Message.fail(e.getError().getErrorMsg());
            }
        }
        JSONObject jsonObject;
        try {
            jsonObject = messageUtil.massSendByOpenId(JSON.toJSONString(map));
        } catch (WxErrorException e) {
            e.printStackTrace();
            return Message.fail(e.getError().getErrorMsg());
        }

        return Message.success("发送成功！",jsonObject);
    }

}
