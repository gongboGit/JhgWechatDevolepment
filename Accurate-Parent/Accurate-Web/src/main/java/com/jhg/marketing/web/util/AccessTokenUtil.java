package com.jhg.marketing.web.util;

import com.alibaba.fastjson.JSONObject;
import com.jhg.marketing.dao.domin.AccessToken;
import com.jhg.marketing.dao.domin.WebConfig;
import com.jhg.marketing.dao.mapper.AccessTokenMapper;
import com.jhg.marketing.web.util.exception.WxError;
import com.jhg.marketing.web.util.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AccessTokenUtil {

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    private static final int EXPIRES_TIME = 60 * 60 * 1000;

    @Autowired
    private WebConfig wechatAccountConfig;


    /**
     * 从接口获取凭证
     *
     * @return
     * @throws WxErrorException
     */
    @CacheEvict(value = "token", allEntries = true)
    public AccessToken getAccessTokenByInterface() {
        //删除所有数据
        accessTokenMapper.delete(null);
        AccessToken token = null;
        String tokenUrl = WeChatUtil.getTokenUrl(wechatAccountConfig.getAppId(), wechatAccountConfig.getAppSecret());
        JSONObject jsonObject = WeChatUtil.httpsRequest(tokenUrl, HttpMethod.GET.toString());

        if (null != jsonObject && !jsonObject.containsKey("errcode")) {
            token = new AccessToken();
            token.setAccessToken(jsonObject.getString("access_token"));
            token.setCreateTime(new Date());
            accessTokenMapper.insert(token);
        } else if (null != jsonObject) {
            try {
                throw new WxErrorException(WxError.fromJson(jsonObject.toJSONString()));
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
        return token;
    }

    /**
     * 获取凭证，手动刷新
     *
     * @return
     */
    @Cacheable(value = "token")
    public String getAccessTokenByDataBase() {
        List<AccessToken> accessTokenList = accessTokenMapper.selectAll();
        if (accessTokenList.size() != 0) {
            AccessToken accessToken = accessTokenList.get(0);
            if (System.currentTimeMillis() - accessToken.getCreateTime().getTime() < EXPIRES_TIME) {
                return accessToken.getAccessToken();
            }
        }
        return getAccessTokenByInterface().getAccessToken();
    }

    /**
     * 每小时自动刷新AccessToken
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void autoFlashAccessToken() {
        getAccessTokenByInterface();
    }

}
