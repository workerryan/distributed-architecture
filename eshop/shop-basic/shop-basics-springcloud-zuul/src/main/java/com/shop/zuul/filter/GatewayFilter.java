package com.shop.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.shop.common.core.utils.SignUtil;
import com.shop.zuul.build.GatewayDirector;
import com.shop.zuul.mapper.entity.Blacklist;
import com.shop.zuul.mapper.entity.BlacklistMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class GatewayFilter extends ZuulFilter {
    private final static Logger log = LoggerFactory.getLogger(GatewayFilter.class);

    @Autowired
    private GatewayDirector gatewayDirector;

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        response.setContentType("UTF-8");
        // 1.获取ip地址
        String ipAddress = getIpAddr(request);
        gatewayDirector.direcot(ctx, ipAddress, response, request);

        return null;
    }

    @Override
    public boolean shouldFilter() {

        return true;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 在方法之前拦截
     *
     * @return
     */
    @Override
    public String filterType() {

        return "pre";
    }

    /**
     * 获取Ip地址
     *
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}