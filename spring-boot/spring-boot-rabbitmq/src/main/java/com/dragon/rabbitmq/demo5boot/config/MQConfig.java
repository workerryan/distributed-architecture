package com.dragon.rabbitmq.demo5boot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author wanglei
 * @since 1.0.0
 */
@SpringBootConfiguration
public class MQConfig {

    /** 邮件队列*/
    private final static String FANOUT_EMAIL_QUEUE = "fanout_email_queue";
    /** 短信队列*/
    private final static String FANOUT_SMS_QUEUE = "fanout_sms_queue";

    private final static String EXCHANGE_NAME = "fanout_exchange";

    /** 1.定义队列邮件*/
    @Bean
    public Queue fanOutEmailQueue() {
        return new Queue(FANOUT_EMAIL_QUEUE);
    }

    @Bean
    public Queue fanOutSmsQueue() {
        return new Queue(FANOUT_SMS_QUEUE);
    }

    /** 2.定义交换机*/
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    /** 3.队列与交换机绑定邮件队列*/
    @Bean
    Binding bindingExchangeEmail(Queue fanOutEmailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutEmailQueue).to(fanoutExchange);
    }

    /** 4.队列与交换机绑定短信队列*/
    @Bean
    Binding bindingExchangeSms(Queue fanOutSmsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutSmsQueue).to(fanoutExchange);
    }
}
