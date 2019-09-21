package com.dragon.cache.mapper;

import com.dragon.cache.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper {
    @Select("SELECT ID ,NAME,AGE FROM users where id=#{id}")

    List<Users> getUser(@Param("id") Long id);
}
