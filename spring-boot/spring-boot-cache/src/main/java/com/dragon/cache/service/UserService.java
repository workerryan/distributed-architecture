package com.dragon.cache.service;

import com.dragon.cache.entity.Users;
import com.dragon.cache.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "userCache")
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Cacheable
    public List<Users> getUser(Long id) {
        logger.info("enter sql query");
        return userMapper.getUser(id);
    }

}
