package com.jhg.marketing.web.util.message;

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
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lxl
 * @since 2019/7/26 20:05
 */
@Component
public class TemplateMessageUtil {

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    public JSONObject setIndustry(Integer primaryIndustryId, Integer secondaryIndustryId) throws WxErrorException {
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("industry_id1", primaryIndustryId);
        map.put("industry_id2", secondaryIndustryId);
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getSetIndustryUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(map));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    public JSONObject getIndustry() throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getIndustryUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.GET.toString());
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    public JSONObject addTemplate(String templateIdShort) throws WxErrorException {
        HashMap<String, String> map = new HashMap<>(1);
        map.put("template_id_short", templateIdShort);
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getAddTemplateUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(map));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    public JSONObject getAllPrivateTemplate() throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getAllPrivateTemplateUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.GET.toString());
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    public JSONObject delPrivateTemplate(String templateId) throws WxErrorException {
        HashMap<String, String> map = new HashMap<>(1);
        map.put("template_id", templateId);
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getDelPrivateTemplateUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(map));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    public JSONObject sendTemplateMessage(String param) throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getSendTemplateMessageUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), param);
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    public List<String> getTemplateKey(String content) {
//        HashMap<String, Object> data = new HashMap<String,Object>();
        LinkedList list = new LinkedList();
        Pattern pattern = Pattern.compile("\\{\\s*\\{.*\\.DATA\\s*\\}\\s*\\}");
        Matcher matcher = pattern.matcher(content);
        //循环，字符串中有多少个符合的，就循环多少次
        while(matcher.find()){
            //每一个符合正则的字符串
            String e = matcher.group();
            Pattern p = Pattern.compile("[^(\\{\\s*\\{\\s*)|(\\.DATA\\s*\\}\\s*\\})]+");
            Matcher m = p.matcher(e);
            m.find();
            String key = m.group();
            list.add(key);
//            HashMap<String, String> t = new HashMap<String,String>();
//            t.put("value", key);
//            t.put("color", "#173177");
//            data.put(key, t);

        }
        return list;
    }
}
