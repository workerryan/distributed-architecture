package com.dragon.rabbitmq.demo3topic;

import com.dragon.rabbitmq.demo1.MQConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wanglei
 * @since 1.0.0
 */
public class SmsConsumer {
    private static final String QUEUE_NAME = "consumer_topic_sms";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("短信消费者启动");
        // 1.创建新的连接
        Connection connection = MQConnectionUtils.newConnection();
        // 2.创建通道
        Channel channel = connection.createChannel();
        // 3.消费者关联队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 4.消费者绑定交换机 参数1 队列 参数2交换机 参数3 routingKey
        channel.queueBind(QUEUE_NAME, Producer.EXCHANGE_NAME, "log.*");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("短信消费者获取生产者消息:" + msg);
            }
        };
        // 5.消费者监听队列消息
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
