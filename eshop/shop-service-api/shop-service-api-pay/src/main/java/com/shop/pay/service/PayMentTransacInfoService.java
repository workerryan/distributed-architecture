package com.shop.pay.service;

import com.shop.common.core.base.entity.BaseResponse;
import com.shop.common.pay.PayMentTransacDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *  服务接口
 */
public interface PayMentTransacInfoService {
	@GetMapping("/tokenByPayMentTransac")
	BaseResponse<PayMentTransacDTO> tokenByPayMentTransac(@RequestParam("token") String token);
}
