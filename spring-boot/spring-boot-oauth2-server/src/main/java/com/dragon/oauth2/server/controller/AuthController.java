package com.dragon.oauth2.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.dragon.oauth2.server.base.BaseApiController;
import com.dragon.oauth2.server.base.ResponseBase;
import com.dragon.oauth2.server.entity.AppEntity;
import com.dragon.oauth2.server.mapper.AppMapper;
import com.dragon.oauth2.server.service.BaseRedisService;
import com.dragon.oauth2.server.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController extends BaseApiController {

    /** 有效期为2小时*/
    private final static long TIME_TOKEN = 60 * 60 * 2;

    private final AppMapper appMapper;
    private final BaseRedisService baseRedisService;

    public AuthController(AppMapper appMapper, BaseRedisService baseRedisService) {
        this.appMapper = appMapper;
        this.baseRedisService = baseRedisService;
    }

    @RequestMapping("/accessToken")
    public ResponseBase getAccessToken(AppEntity appEntity) {
        AppEntity appResult = appMapper.findApp(appEntity);
        if (appResult == null) {
            return setResultError("没有对应机构的认证信息");
        }
        int isFlag = appResult.getIsFlag();
        if (isFlag == 1) {
            return setResultError("您现在没有权限生成对应的AccessToken");
        }
        // ### 获取新的accessToken 之前删除之前老的accessToken
        // 从redis中删除之前的accessToken
        String accessToken = appResult.getAccessToken();
        if(StringUtils.isNotBlank(accessToken)){
            baseRedisService.delKey(accessToken);
        }
        // 生成的新的accessToken
        String newAccessToken = newAccessToken(appResult.getAppId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", newAccessToken);
        return setResultSuccessData(jsonObject);
    }

    private String newAccessToken(String appId) {
        // 使用appid+appsecret 生成对应的AccessToken 保存两个小时
        String accessToken = TokenUtils.getAccessToken();
        // 保证在同一个事物redis 事物中
        // 生成最新的token key为accessToken value 为 appid
        baseRedisService.setString(accessToken, appId, TIME_TOKEN);
        // 表中保存当前accessToken
        appMapper.updateAccessToken(accessToken, appId);
        return accessToken;
    }
}
