package com.dragon.rabbitmq.demo1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 * @author wanglei
 * @since 1.0.0
 */
public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.获取连接
        Connection newConnection = MQConnectionUtils.newConnection();
        // 2.获取通道
        Channel channel = newConnection.createChannel();
        channel.queueDeclare(Producer.QUEUE_NAME, false, false, false, null);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msgString = new String(body, "UTF-8");
                System.out.println("消费者获取消息:" + msgString);
                //手动应答
                channel.basicAck(envelope.getDeliveryTag(), true);
            }
        };
        // 3.监听队列
        channel.basicConsume(Producer.QUEUE_NAME, false, defaultConsumer);

    }
}
