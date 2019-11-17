package com.shop.zuul.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.context.RequestContext;

/***
 * 定义网关请求的Handler
 */
public interface GatewayHandler {

	/**
	 * 每一个Handler具体执行的方法
	 */
	void service(RequestContext ctx, HttpServletRequest req, HttpServletResponse response);

	/**
	 * 指向下一个Handler
	 * @param gatewayHandler  下一个handler
	 */
	void setNextHandler(GatewayHandler gatewayHandler);
}
