package com.shop.member.service;

import com.shop.common.core.base.entity.BaseResponse;
import com.shop.member.dto.input.UserLoginInpDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "会员登陆接口")
public interface MemberLoginService {
	/**
	 * 用户登陆接口
	 * 
	 * @param iserLoginInpDto
	 * @return
	 */
	@PostMapping("/login")
	@ApiOperation(value = "会员用户登陆信息接口")
	BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO iserLoginInpDto);

}
