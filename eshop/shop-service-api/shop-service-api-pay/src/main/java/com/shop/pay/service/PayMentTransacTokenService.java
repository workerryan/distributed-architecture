package com.shop.pay.service;

import com.shop.common.core.base.entity.BaseResponse;
import com.shop.common.pay.PayCratePayTokenDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;


/**
 *  支付交易服务接口
 */
public interface PayMentTransacTokenService {

	/**
	 * 创建支付令牌
	 * 
	 * @return
	 */
	@RequestMapping("/cratePayToken")
	BaseResponse<JSONObject> cratePayToken(@Validated PayCratePayTokenDto payCratePayTokenDto);

}
