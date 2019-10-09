package com.dragon.rabbitmq.demo5boot.controller;

import com.dragon.rabbitmq.demo5boot.producer.FanoutProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
public class ProducerController {
    @Autowired
    private FanoutProducer fanoutProducer;

    @RequestMapping("/sendFanout")
    public String sendFanout(String queueName) {
        fanoutProducer.send(queueName);
        return "success";
    }

}
