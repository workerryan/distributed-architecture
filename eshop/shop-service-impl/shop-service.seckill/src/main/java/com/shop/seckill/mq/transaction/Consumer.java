package com.shop.seckill.mq.transaction;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@Slf4j
public class Consumer {
    @Autowired
    private IntegralService integralService;

    @RabbitListener(queues = "modify_inventory_queue")
    @Transactional
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        try {
            String messageId = message.getMessageProperties().getMessageId();
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);
            log.info("[consumer] messageId:{},msg:{}", messageId, msg);
            Integral integral = JSONObject.parseObject(msg, Integral.class);
            integralService.incrIntegral(integral);
        } catch (Exception e) {
            log.error("消费失败", e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }
}
