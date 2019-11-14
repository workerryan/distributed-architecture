package com.shop.entity.weixin;

public class AppEntity {
    /** 应用id */
    private String appId;
    /** 应用密钥 */
    private String appSecret;

    public AppEntity() {

    }

    public AppEntity(String appId, String appSecret) {
        super();
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
