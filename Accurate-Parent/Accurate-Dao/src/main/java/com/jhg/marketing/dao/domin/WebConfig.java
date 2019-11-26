package com.jhg.marketing.dao.domin;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class WebConfig {

    @Value("${user.wechat.appId}")
    private  String appId;

    @Value("${user.wechat.appSecret}")
    private  String appSecret;

    @Value("${user.wechat.token}")
    private  String token;

    @Value("${user.domain}")
    private  String domain;

    @Value("${shiro.loginUrl}")
    private String loginUrl;
}
