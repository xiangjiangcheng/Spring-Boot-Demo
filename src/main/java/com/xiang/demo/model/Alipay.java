package com.xiang.demo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 支付宝支付接口 属性
 * 读取yml中的配置文件
 *
 * @author xjc
 */
@Component
public class Alipay {
    /**
     * 类型
     */
    @Value("${sdk.alipay.sign-type}")
    private String signType;

    /**
     * APPID
     */
    @Value("${sdk.alipay.app-id}")
    private String appId;

    /**
     * 商户应用私钥
     */
    @Value("${sdk.alipay.private-key}")
    private String privateKey;

    /**
     * 支付宝公钥
     */
    @Value("${sdk.alipay.alipay-public-key}")
    private String alipayPublicKey;

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }
}
