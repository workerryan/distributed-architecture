package com.shop.pay.strategy;

import com.shop.common.pay.PayMentTransacDTO;
import com.shop.pay.mapper.entity.PaymentChannelEntity;

/**
 * @description: 支付接口共同实现行为算法
 */
public interface PayStrategy {

	/**
	 * 
	 * @param pymentChannel
	 *            渠道参数
	 * @param payMentTransacDTO
	 *            支付参数
	 * @return
	 */
	String toPayHtml(PaymentChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO);

}
