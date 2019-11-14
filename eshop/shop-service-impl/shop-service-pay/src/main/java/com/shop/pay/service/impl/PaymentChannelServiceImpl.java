package com.shop.pay.service.impl;

import java.util.List;

import com.shop.common.core.base.api.BaseApiService;
import com.shop.common.core.mapper.MapperUtils;
import com.shop.common.pay.PaymentChannelDTO;
import com.shop.pay.mapper.PaymentChannelMapper;
import com.shop.pay.mapper.entity.PaymentChannelEntity;
import com.shop.pay.service.PaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PaymentChannelServiceImpl extends BaseApiService<List<PaymentChannelDTO>>
		implements PaymentChannelService {
	@Autowired
	private PaymentChannelMapper paymentChannelMapper;

	@Override
	public List<PaymentChannelDTO> selectAll() {
		List<PaymentChannelEntity> paymentChanneList = paymentChannelMapper.selectAll();
		return MapperUtils.mapAsList(paymentChanneList, PaymentChannelDTO.class);
	}

}
