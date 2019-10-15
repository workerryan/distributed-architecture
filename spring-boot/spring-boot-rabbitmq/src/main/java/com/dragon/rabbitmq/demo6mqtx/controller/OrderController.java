package com.dragon.rabbitmq.demo6mqtx.controller;

import com.dragon.rabbitmq.demo6mqtx.producer.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/insert/order")
    public boolean insert(){
        return orderService.createOrder();
    }
}
