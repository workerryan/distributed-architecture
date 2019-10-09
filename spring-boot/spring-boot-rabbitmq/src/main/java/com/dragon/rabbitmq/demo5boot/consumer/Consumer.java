package com.dragon.rabbitmq.demo5boot.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @author wanglei
 * @since 1.0.0
 */
@Component
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = "fanout_email_queue")
    public void email(Message message , Channel channel) throws Exception{
        try{
            String msg = new String(message.getBody(), Charset.forName("UTF-8"));
            logger.info("==>邮件队列收到消息:" + msg);

        }catch(Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
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
}
