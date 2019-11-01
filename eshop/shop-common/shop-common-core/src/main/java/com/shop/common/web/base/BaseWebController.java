package com.shop.common.web.base;

import javax.servlet.http.HttpServletRequest;

import com.shop.common.core.base.entity.BaseResponse;
import com.shop.common.core.constants.Constants;
import org.springframework.ui.Model;


import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;

public class BaseWebController {
	/**
	 * 500页面
	 */
	protected static final String ERROR_500_FTL = "500.ftl";

	/** 接口直接返回true 或者false*/
	protected Boolean isSuccess(BaseResponse<?> baseResp) {
		if (baseResp == null) {
			return false;
		}
		return ! baseResp.getCode().equals(Constants.HTTP_RES_CODE_500);
	}

	/**
	 * 获取浏览器信息
	 */
	protected String webBrowserInfo(HttpServletRequest request) {
		// 获取浏览器信息
		Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
		// 获取浏览器版本号
		Version version = browser.getVersion(request.getHeader("User-Agent"));
		return browser.getName() + "/" + version.getVersion();
	}

	protected void setErrorMsg(Model model, String errorMsg) {
		model.addAttribute("error", errorMsg);
	}

}
