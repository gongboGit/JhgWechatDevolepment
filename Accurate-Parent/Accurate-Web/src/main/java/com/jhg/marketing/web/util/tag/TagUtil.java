package com.jhg.marketing.web.util.tag;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.web.util.AccessTokenUtil;
import com.jhg.marketing.web.util.WeChatUtil;
import com.jhg.marketing.web.util.exception.WxError;
import com.jhg.marketing.web.util.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 微信用户管理接口
 *
 * @author lxl
 * @since 2019/4/8 13:26
 */
@Component
public class TagUtil {

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    /**
     * 创建标签
     *
     * @param tagName
     * @return
     * @throws WxErrorException
     */
    public JSONObject createTag(String tagName) throws WxErrorException {
        HashMap<String, Object> param = new HashMap<>(1);
        HashMap<Object, Object> data = new HashMap<Object, Object>(1) {{
            put("name", tagName);
        }};
        param.put("tag", data);
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getCreateUserTag(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(param));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 获取标签
     *
     * @return
     * @throws WxErrorException
     */
    public JSONObject getTag() throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getUserTagList(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.GET.toString());
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 更新标签
     *
     * @param id
     * @param tagName
     * @return
     * @throws WxErrorException
     */
    public JSONObject updateTag(Integer id, String tagName) throws WxErrorException {
        HashMap<String, Object> param = new HashMap<>(1);
        HashMap<Object, Object> data = new HashMap<Object, Object>(2) {{
            put("name", tagName);
            put("id", id);
        }};
        param.put("tag", data);
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getUpdateUserTagUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(param));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 删除标签
     *
     * @param id
     * @return
     * @throws WxErrorException
     */
    public JSONObject deleteTag(Integer id) throws WxErrorException {
        HashMap<String, Object> param = new HashMap<>(1);
        HashMap<Object, Object> data = new HashMap<Object, Object>(1) {{
            put("id", id);
        }};
        param.put("tag", data);
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getDeleteUserTag(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(param));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 获取标签下粉丝列表
     *
     * @param tagId
     * @param nextOpenId
     * @return
     * @throws WxErrorException
     */
    public JSONObject getUserListByTag(Integer tagId, String nextOpenId) throws WxErrorException {
        HashMap<String, Object> param = new HashMap<>(1);
        param.put("tagid", tagId);
        param.put("next_openid", nextOpenId);
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getUserListByTag(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(param));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 批量为用户打标签
     *
     * @param tagId
     * @param openidList
     * @return
     * @throws WxErrorException
     */
    public JSONObject batchTagging(Integer tagId, List<String> openidList) throws WxErrorException {
        HashMap<String, Object> param = new HashMap<>(1);
        param.put("tagid", tagId);
        param.put("openid_list", openidList);
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getBatchTaggingUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(param));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 批量为用户取消标签
     *
     * @param tagId
     * @param openidList
     * @return
     * @throws WxErrorException
     */
    public JSONObject batchUnTagging(Integer tagId, List<String> openidList) throws WxErrorException {
        HashMap<String, Object> param = new HashMap<>(1);
        param.put("tagid", tagId);
        param.put("openid_list", openidList);
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getBatchUnTaggingUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(param));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 批量为用户取消标签
     *
     * @param openId
     * @return
     * @throws WxErrorException
     */
    public JSONObject getUserTagList(String openId) throws WxErrorException {
        HashMap<String, Object> param = new HashMap<>(1);
        param.put("openid", openId);
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getUserTagListUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(param));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }
}
