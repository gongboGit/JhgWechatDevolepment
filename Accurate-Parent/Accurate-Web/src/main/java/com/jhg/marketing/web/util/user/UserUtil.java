package com.jhg.marketing.web.util.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.web.util.AccessTokenUtil;
import com.jhg.marketing.web.util.StringUtil;
import com.jhg.marketing.web.util.WeChatUtil;
import com.jhg.marketing.web.util.exception.WxError;
import com.jhg.marketing.web.util.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 微信用户管理接口
 *
 * @author lxl
 * @since 2019/4/8 13:26
 */
@Component
public class UserUtil {

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    //TODO 用户标签管理
    //TODO 设置用户备注名

    /**
     * 获取用户基本信息
     *
     * @param openId
     * @return
     * @throws WxErrorException
     */
    public JSONObject getFansInfo(String openId) throws WxErrorException {
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getFansInfoUrl(accessTokenUtil.getAccessTokenByDataBase(), openId), HttpMethod.GET.toString());
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 批量获取用户基本信息
     *
     * @param openIdList
     * @return
     * @throws WxErrorException
     */
    public JSONObject batchGetFansInfo(List<String> openIdList) throws WxErrorException {
        List<Map<String, String>> list = new LinkedList<>();
        for (String s : openIdList) {
            Map<String, String> mapParam = new HashMap<String, String>(2) {
                {
                    put("lang", "zh_CN");
                }
            };
            mapParam.put("openid", s);
            list.add(mapParam);
        }
        Map map = new HashMap<String, List<Map<String, String>>>(1) {
            {
                put("user_list", list);
            }
        };
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getBatchGetFansInfoUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(map));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }

    /**
     * 获取用户列表
     *
     * @param nextOpenId 第一个拉取的OPENID，不填默认从头开始拉取
     * @return {"total":2,"count":2,"data":{"openid":["OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}
     * @throws WxErrorException
     */
    public List<String> getFansList(String nextOpenId) throws WxErrorException {
        List<String> openIdList = new LinkedList<>();
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getFansListUrl(accessTokenUtil.getAccessTokenByDataBase(), StringUtil.isBlank(nextOpenId) ? null : nextOpenId), HttpMethod.GET.toString());
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != null) {
            throw new WxErrorException(wxError);
        }
        String next_openid = (String) jsonObject.get("next_openid");
        if (StringUtil.isNotBlank(next_openid)) {
            openIdList.addAll(((List<String>) ((Map) jsonObject.get("data")).get("openid")));
        } else {
            return null;
        }
        //第一次把所有数据都请求完，next_openid不为null
        if (((int) jsonObject.get("total")) == (int) jsonObject.get("count")) {
            return openIdList;
        }
        while (StringUtil.isNotBlank(next_openid)) {
            JSONObject object = WeChatUtil.httpsRequest(WeChatUtil.getFansListUrl(accessTokenUtil.getAccessTokenByDataBase(), next_openid), HttpMethod.GET.toString());
            if ((int) object.get("count") != 0) {
                openIdList.addAll(((List<String>) ((Map) object.get("data")).get("openid")));
            }
            next_openid = (String) object.get("next_openid");
        }
        return openIdList;
    }
    //TODO 获取用户地理位置
    //TODO 黑名单管理

    /**
     * 获取用户基本信息
     *
     * @param openId
     * @param remark
     * @return
     * @throws WxErrorException
     */
    public JSONObject updateRemark(String openId, String remark) throws WxErrorException {
        HashMap<Object, Object> param = new HashMap<>(2);
        param.put("openid", openId);
        param.put("remark", remark);
        JSONObject jsonObject = WeChatUtil.httpsRequest(WeChatUtil.getUpdateRemarkUrl(accessTokenUtil.getAccessTokenByDataBase()), HttpMethod.POST.toString(), JSON.toJSONString(param));
        WxError wxError = WxError.fromJson(jsonObject);
        if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
        }
        return jsonObject;
    }
}
