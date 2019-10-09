package com.dragon.rabbitmq.demo2fanout;

import com.dragon.rabbitmq.demo1.MQConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 发送邮件的消费者
 * @author wanglei
 * @since 1.0.0
 */
public class FanoutEmailConsumer {
    private static final String QUEUE_NAME = "consumerFanout_email";

    public static void main(String[] args) throws Exception{
        Connection newConnection = MQConnectionUtils.newConnection();
        Channel channel = newConnection.createChannel();

        //声明队列和绑定路由key
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME, FanoutProducer.EXCHANGE_NAME, "");

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("邮件消费者获取生产者消息:" + msg);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
