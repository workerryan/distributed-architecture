package com.shop.pay.rabbitmqConfig;//package com.mayikt.pay.rabbitmqConfig;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RabbitmqConfig {
//
//	// 派单队列
//	public static final String integral_DIC_QUEUE = "integral_queue";
//	// 补单队列，判断订单是否已经被创建
//	public static final String integral_CREATE_QUEUE = "integral_create_queue";
//	// 派单交换机
//	private static final String integral_EXCHANGE_NAME = "integral_exchange_name";
//
//	// 1.定义订单队列
//	@Bean
//	public Queue directIntegralDicQueue() {
//		return new Queue(integral_DIC_QUEUE);
//	}
//
//	// 2.定义补订单队列
//	@Bean
//	public Queue directCreateintegralQueue() {
//		return new Queue(integral_CREATE_QUEUE);
//	}
//
//	// 2.定义交换机
//	@Bean
//	DirectExchange directintegralExchange() {
//		return new DirectExchange(integral_EXCHANGE_NAME);
//	}
//
//	// 3.积分队列与交换机绑定
//	@Bean
//	Binding bindingExchangeintegralDicQueue() {
//		return BindingBuilder.bind(directintegralDicQueue()).to(directintegralExchange()).with("integralRoutingKey");
//	}
//
//	// 3.补单队列与交换机绑定
//	@Bean
//	Binding bindingExchangeCreateintegral() {
//		return BindingBuilder.bind(directCreateintegralQueue()).to(directintegralExchange()).with("integralRoutingKey");
//	}
//
//}
