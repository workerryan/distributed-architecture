package com.dragon.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.order.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @author wanglei
 * @since 1.0.0
 */
public interface OrderMapper extends BaseMapper<Order> {
    @Insert("insert into `order`(name, status, money, sku_id, create_time) values(" +
            "#{name}, #{status}, #{money}, #{skuId}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Order order);
}
