package com.dragon.rabbitmq.demo6mqtx.mapper;

import com.dragon.rabbitmq.demo6mqtx.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author wanglei
 * @since 1.0.0
 */
public interface OrderMapper {

    @Insert("insert into `order` (name, create_time, status, money, sku_id) values(" +
            "#{name}, #{createTime},#{status},#{money},#{skuId}) ")
    int insert(Order order);

    @Select("select id, name, create_time createTime, status, money, sku_id skuId from `order` where name = #{name}")
    Order findOrderId(@Param("id") String name);
}
