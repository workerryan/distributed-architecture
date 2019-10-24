package com.shop.weixin.service;

import com.shop.entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 微信应用服务接口
 */
@Api(tags = "微信服务实现接口")
public interface WeiXinService {
    /**
     * 获取app应用信息
     *
     */
    @ApiOperation(value = "获取微信实体新")
    @GetMapping("/getApp")
    AppEntity getApp();
}
