package com.shop.seckill;

import com.shop.common.core.base.entity.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;


/**
 * 查询秒杀记录
 */
public interface OrderSeckillService {

	@RequestMapping("/getOrder")
	BaseResponse<JSONObject> getOrder(String phone, Long seckillId);

}
