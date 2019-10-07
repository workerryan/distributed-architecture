package com.dragon.order.controller;

import com.dragon.order.entity.Order;
import com.dragon.order.service.OrderService;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/insert")
    public boolean insert(int skuId, int i){
        return orderService.insert(skuId, i);
    }

    @RequestMapping("/select/{id}")
    public Order selectById(@PathVariable Integer id){
        return orderService.getById(id);
    }
}
