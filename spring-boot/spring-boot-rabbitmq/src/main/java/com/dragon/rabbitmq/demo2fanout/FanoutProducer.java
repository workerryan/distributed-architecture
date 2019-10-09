package com.dragon.rabbitmq.demo2fanout;

import com.dragon.rabbitmq.demo1.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 生产者，交换机类型为Fanout
 * @author wanglei
 * @since 1.0.0
 */
public class FanoutProducer {
    protected static final String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws Exception{
        // 1.获取连接
        Connection newConnection = MQConnectionUtils.newConnection();
        // 2.创建通道
        Channel channel = newConnection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        for (int i = 1; i < 10; i++) {
            String msg = "msg-" + i;
            channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        }

        channel.close();
        newConnection.close();
    }
}
