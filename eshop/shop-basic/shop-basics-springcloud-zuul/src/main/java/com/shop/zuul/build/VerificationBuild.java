package com.shop.zuul.build;

import com.netflix.zuul.context.RequestContext;
import com.shop.common.core.utils.SignUtil;
import com.shop.zuul.mapper.entity.Blacklist;
import com.shop.zuul.mapper.BlacklistMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 参数验证builder
 */
@Component
public class VerificationBuild implements GatewayBuild {
    private final static Logger log = LoggerFactory.getLogger(VerificationBuild.class);

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Override
    public Boolean blackBlock(RequestContext ctx, String ipAddres, HttpServletResponse response) {
        if (StringUtils.isEmpty(ipAddres)) {
            resultError(ctx, "未能够获取到ip地址");
            return false;
        }

        // 2.查询数据库黑名单
        Blacklist meiteBlacklist = blacklistMapper.findBlacklist(ipAddres);
        if (meiteBlacklist != null) {
            //黑名单不向下执行，返回false
            resultError(ctx, "ip:" + ipAddres + ",Insufficient access rights");
            return false;
        }
        log.info(">>>>>>ip:{},验证通过>>>>>>>", ipAddres);
        // 3.将ip地址传递到转发服务中
        response.addHeader("ipAddres", ipAddres);
        log.info(">>>>>>ip:{},验证通过>>>>>>>", ipAddres);
        return true;
    }

    @Override
    public Boolean toVerifyMap(RequestContext ctx, String ipAddres, HttpServletRequest request) {
        // 4.外网传递参数验证
        Map<String, String> verifyMap = SignUtil.toVerifyMap(request.getParameterMap(), false);
        if (!SignUtil.verify(verifyMap)) {
            resultError(ctx, "ip:" + ipAddres + ",Sign fail");
            return false;
        }
        return true;
    }

    private void resultError(RequestContext ctx, String errorMsg) {
        ctx.setResponseStatusCode(401);
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(errorMsg);
    }
}
