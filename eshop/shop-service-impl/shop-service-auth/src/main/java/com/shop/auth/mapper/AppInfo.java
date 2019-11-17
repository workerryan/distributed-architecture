package com.shop.auth.mapper;

/**
 * @author wanglei
 * @since 1.0.0
 */
public class AppInfo {
    private long id;
    private String appId;
    private String appName;
    private String appSecret;
    private String accessToken;
    private int isFlag;

    /** 乐观锁 */
    private Integer revision;
    /** 是否可用 */
    private String availability;

    public AppInfo() {
    }

    public AppInfo(String appId, String appName, String appSecret) {
        this.appId = appId;
        this.appName = appName;
        this.appSecret = appSecret;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(int isFlag) {
        this.isFlag = isFlag;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
