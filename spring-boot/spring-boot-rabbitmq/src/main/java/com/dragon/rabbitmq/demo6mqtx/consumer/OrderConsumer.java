package com.dragon.rabbitmq.demo6mqtx.consumer;

import com.alibaba.fastjson.JSONObject;
import com.dragon.rabbitmq.demo6mqtx.config.RabbitmqConfig;
import com.dragon.rabbitmq.demo6mqtx.entity.Order;
import com.dragon.rabbitmq.demo6mqtx.mapper.OrderMapper;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 监听补单队列消息，目的就是为了在消息发出去之后，主动方应用抛出异常导致事务回滚而导致主动方数据丢失，从而导致消息消费失败的情况
 * @author wanglei
 * @since 1.0.0
 */
@Component
public class OrderConsumer {
    @Autowired
    private OrderMapper orderMapper;

    @RabbitListener(queues = RabbitmqConfig.ORDER_DIC_QUEUE)
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(), "UTF-8");
        System.out.println("补单消费检查订单是否创建" + msg + ",消息id:" + messageId);
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String orderId = jsonObject.getString("orderId");
        if (StringUtils.isEmpty(orderId)) {
            // 手动签收消息,通知mq服务器端删除该消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            return;
        }
        Order orderEntityResul = orderMapper.findOrderId(orderId);
        if (orderEntityResul != null) {
            System.out.println("订单已经存在!");
            // 手动签收消息,通知mq服务器端删除该消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            return;
        }
        System.out.println("订单号码:" + orderId + ",不存在。开始进行补单！");
        Order order = new Order();
        order.setName(orderId);
        order.setCreateTime(new Date());
        order.setStatus(0);
        order.setMoney(5);
        order.setSkuId(6);

        // ##################################################
        // 1.先下单，创建订单 (往订单数据库中插入一条数据)
        int orderResult = orderMapper.insert(order);
        System.out.println("orderResult:" + orderResult);
        if (orderResult >= 0) {
            // 手动签收消息,通知mq服务器端删除该消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            return;
        }
        // 重试进行补偿 多次失败 使用日志记录 采用定时job检查或者 人工补偿

    }
}
