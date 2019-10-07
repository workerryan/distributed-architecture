package com.dragon.order.service;

import com.codingapi.tx.annotation.TxTransaction;
import com.dragon.order.entity.Order;
import com.dragon.order.feign.StockFeign;
import com.dragon.order.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author wanglei
 * @since 1.0.0
 */
@Service
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private Random random = new Random();

    private final OrderMapper orderMapper;

    private final StockFeign stockFeign;

    public OrderService(OrderMapper orderMapper, StockFeign stockFeign) {
        this.orderMapper = orderMapper;
        this.stockFeign = stockFeign;
    }

    @TxTransaction(isStart = true)
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(int skuId, int i){
        Order order = new Order();
        order.setName(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setMoney(random.nextInt(5000));
        order.setSkuId(skuId);
        order.setStatus(1);
        order.setCreateTime(new Date());
        int insert = orderMapper.insert(order);

        if(insert != 1){
            logger.error("插入订单失败");
        }

        logger.info("开始扣减库存 ");
        boolean feignResult = stockFeign.descStock(skuId);
        logger.info("远程调用feign的结果为 " + feignResult);

        int result = 1 / i;

        return true;
    }

    public Order getById(int id){
        return orderMapper.selectById(id);
    }
}
