package com.dragon.limit.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能说明: 自定义服务限流注解框架 原理：参考：RateLimiter <br>
 * @author bbc
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExtRateLimiter {

    /** 以每秒为单位固定的速率值往令牌桶中添加令牌，该参数就是固定速率的参数*/
    double permitsPerSecond();

    /** 在规定的毫秒数中,如果没有获取到令牌的话，则直接走服务降级处理*/
    long timeout();
}
