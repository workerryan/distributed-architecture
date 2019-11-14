package com.shop.pay.service;

import com.shop.common.core.base.entity.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

/**
 * @description: 根据不同的渠道id(支付方式) 返回不同的支付提交表单
 */
public interface PayContextService {
	@GetMapping("/toPayHtml")
	BaseResponse<JSONObject> toPayHtml(@RequestParam("channelId") String channelId,
											  @RequestParam("payToken") String payToken);

}
