package com.shop.pay.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * Rabbitmq 关联队列相关配置
 * 
 * @description:
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@Component
public class RabbitmqConfig {

	// 添加积分队列
	public static final String INTEGRAL_DIC_QUEUE = "integral_queue";
	// 支付补偿队列
	public static final String INTEGRAL_CREATE_QUEUE = "integral_create_queue";
	// 积分交换机
	private static final String INTEGRAL_EXCHANGE_NAME = "integral_exchange_name";

	// 1.添加积分队列
	@Bean
	public Queue directIntegralDicQueue() {
		return new Queue(INTEGRAL_DIC_QUEUE);
	}

	// 2.定义支付补偿队列
	@Bean
	public Queue directCreateintegralQueue() {
		return new Queue(INTEGRAL_CREATE_QUEUE);
	}

	// 2.定义交换机
	@Bean
	DirectExchange directintegralExchange() {
		return new DirectExchange(INTEGRAL_EXCHANGE_NAME);
	}

	// 3.积分队列与交换机绑定
	@Bean
	Binding bindingExchangeintegralDicQueue() {
		return BindingBuilder.bind(directIntegralDicQueue()).to(directintegralExchange()).with("integralRoutingKey");
	}

	// 3.补偿队列与交换机绑定
	@Bean
	Binding bindingExchangeCreateintegral() {
		return BindingBuilder.bind(directCreateintegralQueue()).to(directintegralExchange()).with("integralRoutingKey");
	}

}
