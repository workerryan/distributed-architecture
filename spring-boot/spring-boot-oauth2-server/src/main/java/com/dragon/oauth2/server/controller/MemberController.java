package com.dragon.oauth2.server.controller;

import com.dragon.oauth2.server.base.BaseApiController;
import com.dragon.oauth2.server.base.ResponseBase;
import com.dragon.oauth2.server.entity.AppEntity;
import com.dragon.oauth2.server.mapper.AppMapper;
import com.dragon.oauth2.server.service.BaseRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openApi")
public class MemberController extends BaseApiController {
	private final BaseRedisService baseRedisService;
	private final AppMapper appMapper;

	public MemberController(BaseRedisService baseRedisService, AppMapper appMapper) {
		this.baseRedisService = baseRedisService;
		this.appMapper = appMapper;
	}

	@RequestMapping("/getUser")
	public ResponseBase getUser() {
		return setResultSuccess("获取会员信息接口");
	}
}
