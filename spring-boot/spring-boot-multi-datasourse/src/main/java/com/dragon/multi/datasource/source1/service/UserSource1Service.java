package com.dragon.multi.datasource.source1.service;

import com.dragon.multi.datasource.entity.User;
import com.dragon.multi.datasource.source1.mapper.UserSource1Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wanglei
 * @since 1.0.0
 */
@Slf4j
@Service("userSource1Service")
public class UserSource1Service {
    @Autowired
    private UserSource1Mapper userMapper;

    public User findByName(String name){
        return userMapper.findByName(name);
    }

    /**
     * 添加了事务管理，如果出现多事务管理器错误
     * There was an unexpected error (type=Internal Server Error, status=500).
       No qualifying bean of type 'org.springframework.transaction.PlatformTransactionManager' available: expected single matching bean but found 2: test1TransactionManager,test2TransactionManager
     *
     * 那么需要使用transactionManager指定管理器，比如：
     * @Transactional(transactionManager = "test2TransactionManager")
     * 在SpringBoot1.5的时候，没有指定默认事务管理器
     */
    @Transactional(rollbackFor = Exception.class)
    public int insert(String name, Integer age){
        int result = userMapper.insert(name, age);

        int i = 1/age;
        return result;
    }
}
