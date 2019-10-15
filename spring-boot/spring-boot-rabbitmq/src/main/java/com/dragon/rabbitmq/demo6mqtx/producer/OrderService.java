package com.dragon.rabbitmq.demo6mqtx.producer;

import com.alibaba.fastjson.JSONObject;
import com.dragon.rabbitmq.demo6mqtx.entity.Order;
import com.dragon.rabbitmq.demo6mqtx.mapper.OrderMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * 通过确认机制来保证消息的可靠传递
 * @author wanglei
 * @since 1.0.0
 */
@Service
public class OrderService  implements RabbitTemplate.ConfirmCallback{
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean createOrder(){
        Order order = new Order();
        String name = UUID.randomUUID().toString().replaceAll("-", "");
        order.setName(name);
        order.setCreateTime(new Date());
        order.setStatus(0);
        order.setMoney(5);
        order.setSkuId(6);

        int result = orderMapper.insert(order);
        if(result < 1){
            throw new RuntimeException("插入订单失败");
        }

        send(name);


        return true;
    }


    private void send(String orderId) {
        JSONObject jsonObect = new JSONObject();
        jsonObect.put("orderId", orderId);
        String msg = jsonObect.toJSONString();
        System.out.println("msg:" + msg);
        // 封装消息
        Message message = MessageBuilder.withBody(msg.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8").setMessageId(orderId).build();
        // 构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(orderId);
        // 发送消息
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend("order_exchange_name", "orderRoutingKey", message, correlationData);

    }

    // 生产消息确认机制
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String orderId = correlationData.getId();
        System.out.println("消息id:" + correlationData.getId());
        if (ack) {
            System.out.println("消息发送确认成功");
        } else {
            send(orderId);
            System.out.println("消息发送确认失败:" + cause);
        }

    }
}
