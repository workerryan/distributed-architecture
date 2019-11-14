package com.shop.pay.service.impl;

import com.shop.pay.mq.producer.IntegralProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

/**
 * 生产者测试消息
 * 
 * 
 * @description:
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@RestController
public class MQTestServiceImpl {
	@Autowired
	private IntegralProducer integralProducer;

	@RequestMapping("/send")
	public String send() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("paymentId", System.currentTimeMillis());
		jsonObject.put("userId", "123456");
		jsonObject.put("integral", 100);
		integralProducer.send(jsonObject);
		return "success";
	}

}
