package com.shop.zuul.build;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 建造者组装类
 */
@Component
public class GatewayDirector {

    @Resource(name = "verificationBuild")
    private GatewayBuild gatewayBuild;

    /**
     * 连接建造者
     */
    public void direcot(RequestContext ctx, String ipAddress, HttpServletResponse response, HttpServletRequest request) {

        /**
         * 黑名单拦截
         */
        Boolean blackBlock = gatewayBuild.blackBlock(ctx, ipAddress, response);
        if (!blackBlock) {
            //false就return，不走后面的代码
            return;
        }

        /**
         * 参数验证
         */
        Boolean verifyMap = gatewayBuild.toVerifyMap(ctx, ipAddress, request);
        if (!verifyMap) {
            return;
        }
    }

}
