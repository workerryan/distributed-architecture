package com.dragon.oauth2.server.utils;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class WeiXinUtils {
	@Value("${wx.appid}")
	private String appId;
	@Value("${wx.secret}")
	private String secret;
	@Value("${wx.redirecturi}")
	private String redirectUri;
	@Value("${wx.authorizedUrl}")
	private String authorizedUrl;
	@Value("${wx.access_token}")
	private String accessToken;
	@Value("${wx.userinfo}")
	private String userinfo;

	public String getAuthorizedUrl() {
		return authorizedUrl.replace("APPID", appId).replace("REDIRECT_URI", URLEncoder.encode(redirectUri));
	}

	public String getAccessTokenUrl(String code) {
		return accessToken.replace("APPID", appId).replace("SECRET", secret).replace("CODE", code);
	}

	public String getUserInfo(String accessToken, String openId) {
		return userinfo.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
	}

}
