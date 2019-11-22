package com.shop.seckill.mq.transaction.mapper;

import com.shop.seckill.mq.transaction.Integral;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IntegralMapper {
    @Select("select id from shop_integral where phone = #{phone}")
    Integer getIdByPhone(@Param("phone") String phone);

    @Insert("insert into shop_integral(integral, phone) values(#{integral}, #{phone})")
    int insert(Integral integral);

    @Update("update shop_integral set integral = integral + #{incrIntegral} where id = #{id}")
    int updateIntegral(@Param("id") Integer id, @Param("incrIntegral") int integral);

}
