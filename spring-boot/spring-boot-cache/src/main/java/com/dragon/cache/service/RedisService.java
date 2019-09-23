package com.dragon.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key, Object object) {
        // 开启事务权限
        // stringRedisTemplate.setEnableTransactionSupport(true);
        try {
            // 开启事务 begin
            // stringRedisTemplate.multi();
            String value = (String) object;
            stringRedisTemplate.opsForValue().set(key, value);
            // 提交事务
            // stringRedisTemplate.exec();
        } catch (Exception e) {
            // 需要回滚事务
            // stringRedisTemplate.discard();
        }
    }

    public void setSet(String key, Object object) {
        Set<String> value = (Set<String>) object;
        for (String oj : value) {
            stringRedisTemplate.opsForSet().add(key, oj);
        }
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
