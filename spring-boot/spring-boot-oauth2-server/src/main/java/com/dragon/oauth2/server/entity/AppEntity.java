package com.dragon.oauth2.server.entity;

/**
 * @author wanglei
 * @since 1.0.0
 */
public class AppEntity {

        private long id;
        private String appId;
        private String appName;
        private String appSecret;
        private String accessToken;
        private int isFlag;

        /**
         * @return the id
         */
        public long getId() {
            return id;
        }

        /**
         * @param id
         *            the id to set
         */
        public void setId(long id) {
            this.id = id;
        }

        /**
         * @return the appId
         */
        public String getAppId() {
            return appId;
        }

        /**
         * @param appId
         *            the appId to set
         */
        public void setAppId(String appId) {
            this.appId = appId;
        }

        /**
         * @return the appName
         */
        public String getAppName() {
            return appName;
        }

        /**
         * @param appName
         *            the appName to set
         */
        public void setAppName(String appName) {
            this.appName = appName;
        }

        /**
         * @return the appSecret
         */
        public String getAppSecret() {
            return appSecret;
        }

        /**
         * @param appSecret
         *            the appSecret to set
         */
        public void setAppSecret(String appSecret) {
            this.appSecret = appSecret;
        }

        /**
         * @return the isFlag
         */
        public int getIsFlag() {
            return isFlag;
        }

        /**
         * @param isFlag
         *            the isFlag to set
         */
        public void setIsFlag(int isFlag) {
            this.isFlag = isFlag;
        }

        /**
         * @return the accessToken
         */
        public String getAccessToken() {
            return accessToken;
        }

        /**
         * @param accessToken
         *            the accessToken to set
         */
        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
}
