/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.dragon.oauth2.server.utils;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;

public class TokenUtils {

	@RequestMapping("/getToken")
	public static String getAccessToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
