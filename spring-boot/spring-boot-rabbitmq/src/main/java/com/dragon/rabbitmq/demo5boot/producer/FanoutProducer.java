package com.dragon.rabbitmq.demo5boot.producer;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wanglei
 * @since 1.0.0
 */
@Component
public class FanoutProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String queueName, int num) {
        JSONObject jsonObject = new JSONObject();

        String msg = "my_fanout_msg:" + new Date();
        jsonObject.put("content", msg);
        jsonObject.put("num", num);

        amqpTemplate.convertAndSend(queueName, jsonObject.toJSONString());
    }
}
