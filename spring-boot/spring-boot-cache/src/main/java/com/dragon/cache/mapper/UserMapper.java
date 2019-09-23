package com.dragon.cache.mapper;

import com.dragon.cache.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT ID ,NAME,AGE FROM users where id=#{id}")
    Users getUser(@Param("id") Long id);
}
