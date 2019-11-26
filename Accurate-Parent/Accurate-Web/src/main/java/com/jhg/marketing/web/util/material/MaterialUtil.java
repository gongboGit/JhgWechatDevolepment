package com.jhg.marketing.web.util.material;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.web.util.AccessTokenUtil;
import com.jhg.marketing.web.util.HttpClientUtils;
import com.jhg.marketing.web.util.WeChatUtil;
import com.jhg.marketing.web.util.exception.WxError;
import com.jhg.marketing.web.util.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信素材管理
 *
 * @author lxl
 * @since 5/28/2019 4:12 PM
 */

@Component
public class MaterialUtil {

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    /**
     * 新增图文永久素材
     *
     * @param news 图文json字符串
     * @return {"media_id":MEDIA_ID}
     */
    public JSONObject addNews(String news) throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getNewsMaterialUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), news);
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 上传图文消息内的图片获取URL
     *
     * @param filePath 图片绝对路径
     * @return {"url":URL}
     */
    public JSONObject uploadNewsImg(String filePath) throws WxErrorException {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            try {
                throw new IOException("文件不存在");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String result = HttpClientUtils.sendHttpPost(WeChatUtil.getMaterialImgUrl(accessTokenUtil.getAccessTokenByDataBase()), file);
        WxError wxError = WxError.fromJson(result);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }

        return JSONObject.parseObject(result);
    }

    /**
     * 新增其他类型永久素材
     *
     * @param type    图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param fileUrl 文件的绝对路径
     * @param params  视频的额外数据 {"title":VIDEO_TITLE,"introduction":INTRODUCTION}
     * @return {"media_id":MEDIA_ID,"url":URL} 仅新增图片素材时会返回url
     * @throws WxErrorException
     */
    public JSONObject addMaterial(String type, String fileUrl, Map<String, String> params) throws WxErrorException {
        File file = new File(fileUrl);
        if (!file.exists()) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(-2).setErrorMsg("文件不存在").build());
        }
        String fileName = file.getName();
        //获取后缀名
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        long length = file.length();
        //此处做判断是为了尽可能的减少对微信API的调用次数
        if (!WeChatUtil.type_fix.get(type).contains(suffix)) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(40005).setErrorMsg("不合法的文件类型").build());
        }
        if (length > WeChatUtil.type_length.get(type)) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(40006).setErrorMsg("不合法的文件大小").build());
        }
        String url = WeChatUtil.getAddMaterialUrl(accessTokenUtil.getAccessTokenByDataBase(), type);
        String result = HttpClientUtils.sendHttpPost(url, file, params);
        WxError wxError = WxError.fromJson(result);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }

        return JSONObject.parseObject(result);
    }

    /**
     * 获取永久素材
     *
     * @param mediaId
     * @return
     * @throws WxErrorException
     */
    public JSONObject getMaterial(String mediaId) throws WxErrorException {
        String url = WeChatUtil.getMaterialUrl(accessTokenUtil.getAccessTokenByDataBase());
        Map map = new HashMap<String, String>(1) {{
            put("media_id", mediaId);
        }};
        JSONObject jsonObject = WeChatUtil.httpsRequest(url, HttpMethod.POST.toString(), JSON.toJSONString(map));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 永久素材下载-包含图片、语音、缩略图
     *
     * @param mediaId 多媒体素材ID
     * @param file    文件夹目录 例如D://down
     * @return File
     * @throws WxErrorException
     */
    public File downlodMateria(String mediaId, File file)
            throws WxErrorException {
        String url = WeChatUtil.getMaterialUrl(accessTokenUtil.getAccessTokenByDataBase());
        Map<String, String> map = new HashMap<String, String>(1) {{
            put("media_id", mediaId);
        }};
        File obj = HttpClientUtils.sendHttpPostFile(url, map, file);
        if (null == obj) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(-3).setErrorMsg("下载出错").build());
        }

        return obj;
    }

    /**
     * 删除永久素材
     *
     * @param mediaId
     * @return 成功时，errcode将为0
     */
    public JSONObject delMaterial(String mediaId) throws WxErrorException {
        Map map = new HashMap<String, String>(1) {{
            put("media_id", mediaId);
        }};
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getDelMaterialUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(map));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 修改永久图文素材
     *
     * @param news 图文json字符串 {"media_id":MEDIA_ID,"index":INDEX,"articles": ARTICLES}}
     * @return 成功时，errcode将为0
     */
    public JSONObject updateNewsMaterial(String news) throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getUpdateNewsMaterialUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), news);
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 获取素材总数
     *
     * @return {
     * "voice_count":COUNT,
     * "video_count":COUNT,
     * "image_count":COUNT,
     * "news_count":COUNT
     * }
     */
    public JSONObject getMaterialCount() throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getMaterialCountUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.GET.toString());
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 获取素材列表
     *
     * @param type   素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count  返回素材的数量，取值在1到20之间
     * @return
     */
    public JSONObject getBatchMaterial(String type, int offset, int count) throws WxErrorException {
        Map map = new HashMap<String, Object>(3) {{
            put("type", type);
            put("offset", offset);
            put("count", count);
        }};
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getBatchMaterialUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(map));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 临时素材添加
     *
     * @param type    素材类型（image/voice/video/thumb）
     * @param fileUrl 文件的绝对路径
     * @return
     * @throws WxErrorException
     */
    public JSONObject addMedia(String type, String fileUrl) throws WxErrorException {

        File file = new File(fileUrl);
        if (!file.exists()) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(-2).setErrorMsg("文件不存在").build());
        }
        String fileName = file.getName();
        //获取后缀名
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        //文件大小，单位：b
        long length = file.length();
        //此处做判断是为了尽可能的减少对微信API的调用次数
        if (!WeChatUtil.media_fix.get(type).contains(suffix)) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(40005).setErrorMsg("不合法的文件类型").build());
        }
        if (length > WeChatUtil.type_length.get(type)) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(40006).setErrorMsg("不合法的文件大小").build());
        }
        String result = HttpClientUtils.sendHttpPost(WeChatUtil.getUploadMediaUrl(accessTokenUtil.getAccessTokenByDataBase(), type), file, null);
        WxError wxError = WxError.fromJson(result);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }

        return JSONObject.parseObject(result);
    }

    /**
     * 获取临时素材 视频
     *
     * @param mediaId 临时素材的media_id
     * @return {"video_url":DOWN_URL}
     * @throws WxErrorException
     */
    public JSONObject getTemporaryMediaMaterial(String mediaId) throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getTemporaryMediaMaterialUrl(accessTokenUtil.getAccessTokenByDataBase(), mediaId), HttpMethod.POST.toString());
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 临时素材下载-包含图片、语音、缩略图、（视频不知道能行不）
     *
     * @param mediaId 临时素材Id
     * @param file    要下载到的目录 例如D://temp
     * @return
     * @throws WxErrorException
     */
    public File downlodTemporaryMediaMaterial(String mediaId, File file) throws WxErrorException {
        File obj = HttpClientUtils.sendHttpGetFile(WeChatUtil.getTemporaryMediaMaterialUrl(accessTokenUtil.getAccessTokenByDataBase(), mediaId), file);
        if (null == obj) {
            throw new WxErrorException(WxError.newBuilder().setErrorCode(-3).setErrorMsg("下载出错").build());
        }
        return obj;
    }

}
