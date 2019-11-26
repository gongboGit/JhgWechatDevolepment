package com.jhg.marketing.web.config;

import com.jhg.marketing.dao.domin.WebConfig;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatMpConfig {
    @Autowired
    private WebConfig webConfig;


    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(webConfig.getAppId());
        wxMpInMemoryConfigStorage.setSecret(webConfig.getAppSecret());
        wxMpInMemoryConfigStorage.setToken(webConfig.getToken());
//        wxMpInMemoryConfigStorage.setAesKey("m8lg6owoQN2xK79ku5JW0JJXxz7QepX2BWlj5NHFsi8");
        return wxMpInMemoryConfigStorage;
    }
}
