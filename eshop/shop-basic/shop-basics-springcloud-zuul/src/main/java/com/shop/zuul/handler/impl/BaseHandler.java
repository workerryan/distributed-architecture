package com.shop.zuul.handler.impl;

import com.netflix.zuul.context.RequestContext;
import com.shop.zuul.handler.GatewayHandler;

/**
 * BaseHandler
 */
public class BaseHandler {
	protected GatewayHandler nextGatewayHandler;

	/**
	 * 传进来的就是下一个的Handler
	 * @param nextGatewayHandler 下一个Handler
	 */
	public void setNextHandler(GatewayHandler nextGatewayHandler) {
		this.nextGatewayHandler = nextGatewayHandler;
	}

	public void resultError(Integer code, RequestContext ctx, String errorMsg) {
		ctx.setResponseStatusCode(code);
		// 网关响应为false 不会转发服务
		ctx.setSendZuulResponse(false);
		ctx.setResponseBody(errorMsg);
		ctx.getResponse().setContentType("text/html;charset=UTF-8");
	}
}
