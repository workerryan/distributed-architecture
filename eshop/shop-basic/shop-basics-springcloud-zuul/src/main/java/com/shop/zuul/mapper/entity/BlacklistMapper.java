package com.shop.zuul.mapper.entity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BlacklistMapper {
    @Select("select ID AS ID ,ip_addres AS ipAddress,restriction_type  as restrictionType, availability  as availability " +
            "from shop_blacklist " +
            "where  ip_addres =#{ipAddres} and  restriction_type='1' ")
    Blacklist findBlacklist(String ipAddres);
}
