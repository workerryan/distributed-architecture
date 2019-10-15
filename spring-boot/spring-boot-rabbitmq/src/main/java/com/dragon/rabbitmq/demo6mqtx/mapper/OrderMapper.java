package com.dragon.rabbitmq.demo6mqtx.mapper;

import com.dragon.rabbitmq.demo6mqtx.entity.Order;
import org.apache.ibatis.annotations.Insert;

/**
 * @author wanglei
 * @since 1.0.0
 */
public interface OrderMapper {

    @Insert("insert into `order` (name, create_time, status, money, sku_id) values(" +
            "#{name}, #{createTime},#{status},#{money},#{skuId}) ")
    int insert(Order order);
}
