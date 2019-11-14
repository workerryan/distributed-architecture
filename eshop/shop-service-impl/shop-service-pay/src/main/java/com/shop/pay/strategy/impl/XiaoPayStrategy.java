package com.shop.pay.strategy.impl;


import com.shop.common.pay.PayMentTransacDTO;
import com.shop.pay.mapper.entity.PaymentChannelEntity;
import com.shop.pay.strategy.PayStrategy;

public class XiaoPayStrategy implements PayStrategy {

	@Override
	public String toPayHtml(PaymentChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO) {
		return "小米支付from表单提交";
	}
	//com.mayikt.pay.strategy.impl.XiaoPayStrategy

}
