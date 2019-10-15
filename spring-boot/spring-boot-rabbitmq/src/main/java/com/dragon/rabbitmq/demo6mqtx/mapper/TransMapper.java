package com.dragon.rabbitmq.demo6mqtx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author wanglei
 * @since 1.0.0
 */
public interface TransMapper {

    @Insert("insert into trans(id) values(#{id})")
    void insert(@Param("id") String id);
}
