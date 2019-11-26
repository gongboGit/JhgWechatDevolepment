package com.jhg.marketing.web.util.weChatPay;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author lxl
 * @since 2019/8/20 10:39
 */
@Component
@Slf4j
public class WXPayConfigImpl extends WXPayConfig {

    @Value("${user.wechat.appId}")
    private String appId;
    @Value("${user.wechat.mchId}")
    private String mchId;
    @Value("${user.wechat.paternerKey}")
    private String paternerKey;
    //@Value("${user.wechat.certPath}")
    //private String certPath;
    private byte[] certData;

    public WXPayConfigImpl() throws Exception {
        InputStream certStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/WxPay/apiclient_cert.p12");
        this.certData = IOUtils.toByteArray(certStream);
        certStream.close();
    }


    public void setKey(String key) {
        this.paternerKey = key;
    }

    @Override
    public String getAppID() {
        return this.appId;
    }

    @Override
    public String getMchID() {
        return this.mchId;
    }

    @Override
    public String getKey() {
        return this.paternerKey;
    }

    @Override
    InputStream getCertStream() {
        return new ByteArrayInputStream(this.certData);
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
    }
}
