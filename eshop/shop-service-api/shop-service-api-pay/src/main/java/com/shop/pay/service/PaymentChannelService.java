package com.shop.pay.service;

import java.util.List;

import com.shop.common.pay.PaymentChannelDTO;
import org.springframework.web.bind.annotation.GetMapping;


public interface PaymentChannelService {
	/**
	 * 查询所有支付渠道
	 * 
	 * @return
	 */
	@GetMapping("/selectAll")
	public List<PaymentChannelDTO> selectAll();
}
