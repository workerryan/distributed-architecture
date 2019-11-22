package com.shop.seckill.mq.transaction;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class Producer implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg){
        String msgId = UUID.randomUUID().toString().replace("-", "");
        log.info("[producer] 需要发送的消息为 " + msgId + " , " + msg);
        // 封装消息
        Message message = MessageBuilder.withBody(msg.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8").setMessageId(msgId)
                .build();
        // 构建回调返回的数据（消息id）
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(msg);
        rabbitTemplate.convertAndSend("modify_exchange_name", "modifyRoutingKey", message, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("[producer] 消息id:" + correlationData.getId());
        if (ack) {
            log.info("[producer] 使用MQ消息确认机制确保消息一定要投递到MQ中成功");
            return;
        }
        send(correlationData.getId());
        log.info("[producer] 使用MQ消息确认机制投递到MQ中失败");
    }
}
