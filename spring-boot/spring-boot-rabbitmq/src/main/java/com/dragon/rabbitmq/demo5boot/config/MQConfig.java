package com.dragon.rabbitmq.demo5boot.config;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

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

    /** 死信队列 交换机标识符 */
    public static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";
    /** 死信队列交换机绑定键标识符 */
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    /** 定义死信队列相关信息 */
    public final static String deadQueueName = "dead_queue";
    public final static String deadRoutingKey = "dead_routing_key";
    public final static String deadExchangeName = "dead_exchange";

    /** 1.定义队列邮件，同时将该队列绑定到死信队列交换机上*/
    @Bean
    public Queue fanOutEmailQueue() {
        // 将普通队列绑定到死信队列交换机上
        Map<String, Object> args = new HashMap<>(2);
        args.put(DEAD_LETTER_QUEUE_KEY, deadExchangeName);
        args.put(DEAD_LETTER_ROUTING_KEY, deadRoutingKey);
        return new Queue(FANOUT_EMAIL_QUEUE, true, false, false, args);
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

    /**
     * 配置死信队列
     *
     * @return
     */
    @Bean
    public Queue deadQueue() {
        return new Queue(deadQueueName, true);
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(deadExchangeName);
    }

    @Bean
    public Binding bindingDeadExchange(Queue deadQueue, DirectExchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(deadRoutingKey);
    }
}
