package com.dragon.service;

import org.springframework.stereotype.Component;

/**
 * @author wanglei
 * @since 1.0.0
 */
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class RedisToken {
    @Autowired
    private BaseRedisService baseRedisService;
    private static final long TOKEN_TIME_OUT = 60 * 60;

    public String getToken() {
        // 生成token 规则保证 临时且唯一 不支持分布式场景 分布式全局ID生成规则
        String token = "token" + UUID.randomUUID();
        // 如何保证token临时 （缓存）使用redis 实现缓存
        baseRedisService.setString(token, token, TOKEN_TIME_OUT);
        return token;
    }

    /**
     * 使用token解决幂等性
     * 1.在调用接口之前生成对应的令牌(Token), 存放在Redis
     * 2.调用接口的时候，将该令牌放入的请求头中
     * 3.接口获取对应的令牌,如果能够获取该令牌(将当前令牌删除掉) 就直接执行该访问的业务逻辑
     * 4.接口获取对应的令牌,如果获取不到该令牌 直接返回请勿重复提交
     *
     * @param tokenKey tokenKey
     * @return 是否找到对应的token
     */
    public synchronized boolean findToken(String tokenKey) {
        // 3.接口获取对应的令牌,如果能够获取该(从redis获取令牌)令牌(将当前令牌删除掉) 就直接执行该访问的业务逻辑
        Object token = baseRedisService.getString(tokenKey);
        if(token == null){
            return false;
        }
        String tokenValue = (String) token;
        if (StringUtils.isEmpty(tokenValue)) {
            return false;
        }
        // 保证每个接口对应的token 只能访问一次，保证接口幂等性问题
        baseRedisService.delKey(tokenValue);
        return true;
    }
}
