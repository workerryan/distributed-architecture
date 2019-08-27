package com.dragon.multi.datasource.source2.service;

import com.dragon.multi.datasource.entity.User;
import com.dragon.multi.datasource.source1.mapper.UserSource1Mapper;
import com.dragon.multi.datasource.source2.mapper.UserSource2Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * source1的用户Service
 * @author wanglei
 * @since 1.0.0
 */
@Slf4j
@Service("userSource2Service")
public class UserSource2Service {
    @Autowired
    private UserSource2Mapper userMapper;

    public User findByName(String name){
        return userMapper.findByName(name);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(String name, Integer age){
        int result = userMapper.insert(name, age);
        int i = 1/age;
        return result;

    }
}
