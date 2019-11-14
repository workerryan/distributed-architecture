package com.shop.zuul.build;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 建造者模式
 * 网关权限build
 */
public interface GatewayBuild {
    /**
     * 黑名单拦截
     */
    Boolean blackBlock(RequestContext ctx, String ipAddress, HttpServletResponse response);

    /**
     * 参数验证
     */
    Boolean toVerifyMap(RequestContext ctx, String ipAddress, HttpServletRequest request);
}
