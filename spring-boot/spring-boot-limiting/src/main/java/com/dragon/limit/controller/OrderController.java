package com.dragon.limit.controller;

import com.dragon.limit.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /** create 方法中传入一个参数，这个参数是以每秒为单位固定的速率值，当传入的值为1的时候，就表示 1r/s 每秒中往桶中存入一个令牌
     * 这是一个独立线程
     * */
    private RateLimiter rateLimiter = RateLimiter.create(1);

    /** 限流处理 限流正常要放在网关。
     *  上面设置的1，就相当于该接口每秒钟时间 只能支持一个客户端访问 */
    @RequestMapping("/addOrder")
    public String addOrder() {
        // 1. 客户端从桶中获取对应的令牌，返回值是double结果，这个结果表示 从桶中拿到令牌等待时间.
        // 2. 如果获取不到令牌，该方法会一直等待。所以不能使用该方法。
        // double acquire = rateLimiter.acquire();
        // System.out.println("从桶中获取令牌等待的时间:" + acquire);

        // 需要设置服务降级处理（相当于配置在规定时间内如果没有获取到令牌的话，直接走服务降级。）
        // 如果在500毫秒内如果没有获取到令牌的话，则直接走服务降级处理
        boolean tryAcquire = rateLimiter.tryAcquire(500, TimeUnit.MILLISECONDS);
        if (!tryAcquire) {
            System.out.println("别抢了， 在抢也是一直等待的， 还是放弃吧！！！");
            return "别抢了， 在抢也是一直等待的， 还是放弃吧！！！";
        }
        // 2. 业务逻辑处理
        boolean addOrderResult = orderService.addOrder();
        if (addOrderResult) {
            System.out.println("恭喜您，抢购成功! 等待时间:" + rateLimiter.acquire());
            return "恭喜您，抢购成功!";
        }
        return "抢购失败!";
    }
}
