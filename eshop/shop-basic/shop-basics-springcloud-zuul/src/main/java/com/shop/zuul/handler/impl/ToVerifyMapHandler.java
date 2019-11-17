package com.shop.zuul.handler.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.zuul.handler.GatewayHandler;
import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/**
 * api验证签名Handler
 */
@Component
@Slf4j
public class ToVerifyMapHandler extends BaseHandler implements GatewayHandler {

	@Override
	public void service(RequestContext ctx, HttpServletRequest req, HttpServletResponse response) {
		log.info(">>>>>>>第三关api验证签名Handler执行>>>>");
		nextGatewayHandler.service(ctx, req, response);
	}

}
