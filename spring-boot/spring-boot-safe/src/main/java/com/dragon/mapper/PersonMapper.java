package com.dragon.mapper;

import com.dragon.entity.Person;
import org.apache.ibatis.annotations.Insert;

/**
 * @author wanglei
 * @since 1.0.0
 */
public interface PersonMapper {

    @Insert("insert into t_person(name, age) values(#{name}, #{age})")
    Integer save(Person person);
}
