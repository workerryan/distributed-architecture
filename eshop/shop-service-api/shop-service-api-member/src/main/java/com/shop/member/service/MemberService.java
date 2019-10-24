package com.shop.member.service;

import com.shop.entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 会员服务接口
 */
@Api(tags = "会员服务实现接口")
public interface MemberService {
    /**
     * 会员服务接口调用微信接口
     */
    @ApiOperation(value = "会员服务调用微信服务")
    @GetMapping("/memberInvokeWeixin")
    AppEntity memberInvokeWeixin();
}
