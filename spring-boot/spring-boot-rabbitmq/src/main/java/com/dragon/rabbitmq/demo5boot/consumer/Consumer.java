package com.dragon.rabbitmq.demo5boot.consumer;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * 在默认情况下，如果消费者出现异常了，会自动补偿。
 * 注解@RabbitListener底层使用AOP拦截，如果没有抛出异常，则会自动提交RabbitMQ事务，
 * 如果消费者程序业务逻辑出现异常，则会自动实现补偿机制，该消息会一直在RabbitMQ中，不会被删除，
 * 一直重试到不抛出异常为准
 * @author wanglei
 * @since 1.0.0
 */
@Component
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = "fanout_email_queue")
    public void email(Message message , @Headers Map<String, Object> headers, Channel channel) throws Exception{
        try {
            String msg = new String(message.getBody(), Charset.forName("UTF-8"));
            logger.info("==>邮件队列收到消息:" + msg);

            JSONObject jsonObject = JSONObject.parseObject(msg);
            String content = jsonObject.getString("content");
            int num = jsonObject.getIntValue("num");

            long tag1 = (long)headers.get(AmqpHeaders.DELIVERY_TAG);
            System.out.println(tag1);
            long tag2 = message.getMessageProperties().getDeliveryTag();
            System.out.println(tag2);

            int i = 1/num;

            channel.basicAck(tag2, false);
        } catch (Exception e) {
            logger.error("邮件队列消费失败",e);
            //拒绝消费，这里将会把消息传递给死信队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    @RabbitListener(queues = "fanout_sms_queue")
    public void sms(Message message , Channel channel) throws Exception{
        try{
            String msg = new String(message.getBody(), Charset.forName("UTF-8"));
            logger.info("==>短信队列收到消息:" + msg);

        }catch(Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }


    @RabbitListener(queues = "dead_queue")
    public void dealQueue(Message message , Channel channel) throws Exception{
        try{
            String msg = new String(message.getBody(), Charset.forName("UTF-8"));
            logger.info("==>死信队列收到消息:" + msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            int i = 1/0;

        }catch(Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
