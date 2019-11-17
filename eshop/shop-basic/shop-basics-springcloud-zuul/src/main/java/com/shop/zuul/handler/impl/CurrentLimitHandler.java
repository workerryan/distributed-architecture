package com.shop.zuul.handler.impl;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.zuul.handler.GatewayHandler;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 秒杀限流
 */
@Component
@Slf4j
public class CurrentLimitHandler extends BaseHandler implements GatewayHandler {
	// 每秒时间存入令牌桶中Token为1个 1s/1r 写在分布式配置中心
	private RateLimiter rateLimiter = RateLimiter.create(1);

	@Override
	public void service(RequestContext ctx, HttpServletRequest req, HttpServletResponse response) {
		// 1.用户实现令牌桶限流
		log.info(">>>第一关API接口限流>>>>>");
		boolean tryAcquire = rateLimiter.tryAcquire(0, TimeUnit.SECONDS);
		if (!tryAcquire) {
			resultError(500, ctx, "当前排队人数过多,请稍后重试....");
			return;
		}
		// 继续走下一个Handler流程判断
		nextGatewayHandler.service(ctx, req, response);

	}

}
