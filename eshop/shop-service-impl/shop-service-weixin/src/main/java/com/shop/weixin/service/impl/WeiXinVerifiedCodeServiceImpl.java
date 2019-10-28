package com.shop.weixin.service.impl;

import com.shop.common.core.base.api.BaseApiService;
import com.shop.common.core.base.entity.BaseResponse;
import com.shop.common.core.constants.Constants;
import com.shop.common.core.utils.RedisUtil;
import com.shop.weixin.service.VerifiedCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 校验微信的code是否正确实现
 * @author wanglei
 */
@RestController
public class WeiXinVerifiedCodeServiceImpl extends BaseApiService<JSONObject> implements VerifiedCodeService {
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public BaseResponse<JSONObject> verifiedCode(String phone, String weixinCode) {
		if (StringUtils.isEmpty(phone)) {
			return setResultError("手机号码不能为空!");
		}
		if (StringUtils.isEmpty(weixinCode)) {
			return setResultError("注册码不能为空!");
		}
		String key = Constants.WEIXINCODE_KEY + phone;
		String code = redisUtil.getString(key);
		if (StringUtils.isEmpty(code)) {
			return setResultError("注册码已经过期,请重新发送验证码");
		}

		if (!code.equals(weixinCode)) {
			return setResultError("注册码不正确");
		}

		redisUtil.delKey(key);

		return setResultSuccess("注册码验证码正确");
	}

}
