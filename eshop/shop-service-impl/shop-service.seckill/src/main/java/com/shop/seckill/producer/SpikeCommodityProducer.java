package com.shop.seckill.producer;

import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 秒杀生产者
 */
@Component
@Slf4j
public class SpikeCommodityProducer implements RabbitTemplate.ConfirmCallback {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Transactional
	public void send(JSONObject jsonObject) {
		String jsonString = jsonObject.toJSONString();
		log.info("[producer] 需要发生的消息为 " + jsonString);
		String messAgeId = UUID.randomUUID().toString().replace("-", "");
		// 封装消息
		Message message = MessageBuilder.withBody(jsonString.getBytes())
				.setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8").setMessageId(messAgeId)
				.build();
		// 构建回调返回的数据（消息id）
		this.rabbitTemplate.setMandatory(true);
		this.rabbitTemplate.setConfirmCallback(this);
		CorrelationData correlationData = new CorrelationData(jsonString);
		rabbitTemplate.convertAndSend("modify_exchange_name", "modifyRoutingKey", message, correlationData);
	}

	// 生产消息确认机制 生产者往服务器端发送消息的时候，采用应答机制
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		log.info("[producer] 消息id:" + correlationData.getId());
		if (ack) {
			log.info("[producer] 消息发送成功");
			return;
		}

		String jsonString = correlationData.getId();
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		// 生产者消息投递失败的话，采用递归重试机制
		send(jsonObject);
		log.info("[producer] 使用MQ消息确认机制投递到MQ中失败");
	}
}
