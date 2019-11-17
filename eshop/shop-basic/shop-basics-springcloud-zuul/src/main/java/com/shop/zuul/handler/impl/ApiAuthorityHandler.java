package com.shop.zuul.handler.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.zuul.handler.GatewayHandler;
import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/**
 * api验证TokenHandler
 *
 */
@Component
@Slf4j
public class ApiAuthorityHandler extends BaseHandler implements GatewayHandler {

	@Override
	public void service(RequestContext ctx, HttpServletRequest req, HttpServletResponse response) {
		log.info(">>>>>>>第四关api验证TokenHandler执行>>>>");
	}

}
