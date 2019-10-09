package com.dragon.rabbitmq.demo4amqp;

import com.dragon.rabbitmq.demo1.MQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wanglei
 * @since 1.0.0
 */
public class Producer {
    protected static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.获取连接
        Connection newConnection = MQConnectionUtils.newConnection();
        // 2.创建通道
        Channel channel = newConnection.createChannel();
        // 3.创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);


        try {
            //开启事务
            channel.txSelect();
            String msg = "test_110";
            System.out.println("生产者发送消息:" + msg);
            // 4.发送消息
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

            int i = 1/0;
            //提交事务
            channel.txCommit();
        } catch (IOException e) {
            e.printStackTrace();
            //回滚事务
            channel.txRollback();
        }finally {
            channel.close();
            newConnection.close();
        }
    }
}
