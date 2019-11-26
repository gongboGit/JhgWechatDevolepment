package com.jhg.marketing.web.util.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.web.util.AccessTokenUtil;
import com.jhg.marketing.web.util.WeChatUtil;
import com.jhg.marketing.web.util.exception.WxError;
import com.jhg.marketing.web.util.exception.WxErrorException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息处理工具
 *
 * @author lxl
 */
@Component
public class MessageUtil {

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    /**
     * 将微信的请求中参数转成map
     *
     * @param request
     * @return
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream in = null;
        try {
            in = request.getInputStream();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element element : list) {
                map.put(element.getName(), element.getText());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 群发：上传图文消息素材
     *
     * @param news 图文json字符串
     * @return {"type":"news","media_id":"MEDIA_ID","created_at":1391857799}
     * @throws WxErrorException
     */
    public JSONObject massSendUploadNews(String news) throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getUploadNewsUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), news);
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 群发：上传视频素材
     *
     * @param mediaId
     * @param title
     * @param description
     * @return {"type":"video","media_id":"MEDIA_ID","created_at":1398848981}
     * @throws WxErrorException
     */
    public JSONObject massSendUploadVideo(String mediaId, String title, String description) throws WxErrorException {
        Map map = new HashMap<String, String>(3) {{
            put("media_id", mediaId);
            put("title", title);
            put("description", description);
        }};
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getMassSendUploadVideoUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(map));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 根据标签进行群发
     *
     * @param msg 消息对应的json字符串
     * @return {"errcode":0,"errmsg":"send job submission success","msg_id":34182,"msg_data_id": 206227730}
     */
    public JSONObject massSendByTag(String msg) throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getMassSendByTagUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), msg);
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 根据OpenID进行群发
     *
     * @param msg 消息对应的json字符串
     * @return {"errcode":0,"errmsg":"send job submission success","msg_id":34182,"msg_data_id": 206227730}
     */
    public JSONObject massSendByOpenId(String msg) throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getMassSendByOpenIdUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), msg);
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 删除群发：只能删除图文消息和视频消息
     *
     * @param msgId        发送出去的消息ID
     * @param articleIndex 要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
     * @return
     * @throws WxErrorException
     */
    public JSONObject deleteMassSend(Integer msgId, Integer articleIndex) throws WxErrorException {
        Map map = new HashMap<String, Integer>(2) {{
            put("msg_id", msgId);
            if (articleIndex != null) {
                put("article_idx", articleIndex);
            }
        }};
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getMassSendDeleteUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(map));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 预览群发消息
     *
     * @param param touser字段都可以改为towxname，这样就可以针对微信号进行预览（而非openID），towxname和touser同时赋值时，以towxname优先
     * @return
     * @throws WxErrorException
     */
    public JSONObject previewMassSend(String param) throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getMassSendPreviewUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), param);
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 查询群发消息发送状态
     *
     * @param msgId {"msg_id": "201053012"}
     * @return {"msg_id":201053012,"msg_status":"SEND_SUCCESS"}
     * @throws WxErrorException
     */
    public JSONObject massSendStatus(Integer msgId) throws WxErrorException {
        Map map = new HashMap<String, Integer>(1) {{
            put("msg_id", msgId);
        }};
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getMassSendStatusUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(map));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    //TODO 1.使用 clientmsgid 参数，避免重复推送
    //TODO 2.控制群发速度
}
