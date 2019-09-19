package com.dragon.oauth2.server.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.dragon.oauth2.server.base.BaseApiController;
import com.dragon.oauth2.server.entity.AppEntity;
import com.dragon.oauth2.server.mapper.AppMapper;
import com.dragon.oauth2.server.service.BaseRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 对token进行拦截
 */
@Component
public class AccessTokenInterceptor extends BaseApiController implements HandlerInterceptor {
    private final BaseRedisService baseRedisService;
    private final AppMapper appMapper;

    public AccessTokenInterceptor(BaseRedisService baseRedisService, AppMapper appMapper) {
        this.baseRedisService = baseRedisService;
        this.appMapper = appMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("开始进入请求地址拦截");
        String accessToken = request.getParameter("accessToken");
        if(StringUtils.isBlank(accessToken)){
            resultError("accessToken is null", response);
             return false;
        }

        Object tokenTemp = baseRedisService.getString(accessToken);
        if(tokenTemp == null){
            resultError("accessToken error", response);
            return false;
        }

        String appId = tokenTemp.toString();
        if(StringUtils.isBlank(appId)){
            resultError("this is accessToken invalid", response);
            return false;
        }

        AppEntity appResult = appMapper.findAppId(appId);
        if(appResult == null){
            resultError("not found app indo", response);
            return false;
        }

        if(appResult.getIsFlag() == 1){
            resultError("limited authority", response);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("处理请求完成后视图渲染之前的处理操作");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("视图渲染之后的操作");
    }

    public void resultError(String errorMsg, HttpServletResponse httpServletResponse) throws IOException {
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.write(JSONObject.toJSONString(setResultError(errorMsg)));
    }
}
