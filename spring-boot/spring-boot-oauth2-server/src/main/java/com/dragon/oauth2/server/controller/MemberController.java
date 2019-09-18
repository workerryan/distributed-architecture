package com.dragon.oauth2.server.controller;

import com.dragon.oauth2.server.base.BaseApiController;
import com.dragon.oauth2.server.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openApi")
public class MemberController extends BaseApiController {

	@RequestMapping("/getUser")
	public ResponseBase getUser() {
		return setResultSuccess("获取会员信息接口");
	}
}
