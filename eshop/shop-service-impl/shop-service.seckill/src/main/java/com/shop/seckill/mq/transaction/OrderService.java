package com.shop.seckill.mq.transaction;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shop.seckill.service.mapper.OrderMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private Producer producer;

    @Transactional(rollbackFor = Exception.class)
    public boolean insertOrder(String goodsId, String phone){
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setStatus(1);
        order.setGoodsId(goodsId);
        order.setPhone(phone);

        orderMapper.insert(order);

        Integral integral = new Integral();
        integral.setPhone(phone);
        integral.setIntegral(5);
        producer.send(JSONObject.toJSONString(integral));

        return true;
    }
}
