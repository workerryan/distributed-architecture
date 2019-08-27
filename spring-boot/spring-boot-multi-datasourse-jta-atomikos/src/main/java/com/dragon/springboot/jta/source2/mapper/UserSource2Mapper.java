package com.dragon.springboot.jta.source2.mapper;

import com.dragon.springboot.jta.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 数据库连接2的Mapper
 * @author wanglei
 * @since 1.0.0
 */
public interface UserSource2Mapper {
    @Select("SELECT * FROM user WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

    @Insert("INSERT INTO user(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);
}
