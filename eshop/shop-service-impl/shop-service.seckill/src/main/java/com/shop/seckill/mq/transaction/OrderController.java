package com.shop.seckill.mq.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/insert")
    public boolean insert(String goodsId, String phone){
       return orderService.insertOrder(goodsId,phone);
    }
}
