package com.shop.pay.service.impl;//package com.mayikt.pay.service.impl;
//
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mayikt.base.BaseApiService;
//import com.mayikt.base.BaseResponse;
//import com.mayikt.pay.compensation.strategy.PaymentCompensationStrategy;
//import com.mayikt.pay.mapper.PaymentChannelMapper;
//import com.mayikt.pay.mapper.PaymentTransactionMapper;
//import com.mayikt.pay.mapper.entity.PaymentChannelEntity;
//import com.mayikt.pay.mapper.entity.PaymentTransactionEntity;
//import com.mayikt.pay.service.PaymentContextCompensationService;
//
//@RestController
//public class PaymentContextCompensationServiceImpl extends BaseApiService<JSONObject>
//		implements PaymentContextCompensationService {
//	@Autowired
//	private PaymentTransactionMapper paymentTransactionMapper;
//	@Autowired
//	private PaymentChannelMapper paymentChannelMapper;
//
//	@Override
//	public BaseResponse<JSONObject> payMentCompensation(String payMentId) {
//		if (StringUtils.isEmpty(payMentId)) {
//			return setResultError("payMentId不能为空");
//		}
//		PaymentTransactionEntity paymentTransaction = paymentTransactionMapper.selectByPaymentId(payMentId);
//		if (paymentTransaction == null) {
//			return setResultError("paymentTransaction为空!");
//		}
//
//		// 2.获取所有的渠道重试id
//		List<PaymentChannelEntity> paymentChannelList = paymentChannelMapper.selectAll();
//		for (PaymentChannelEntity pcl : paymentChannelList) {
//			if (pcl != null) {
//				return compensationStrategy(paymentTransaction, pcl);
//			}
//		}
//
//		return setResultError("没有执行重试任务");
//	}
//
//}
