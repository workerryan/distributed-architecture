package com.shop.member.service.impl;

import com.shop.entity.AppEntity;
import com.shop.member.feign.WeiXinFeign;
import com.shop.member.service.MemberService;
import org.springframework.web.bind.annotation.RestController;


/**
 * 会员实现
 */
@RestController
public class MemberServiceImpl implements MemberService {
    private final WeiXinFeign weiXinFeign;

    public MemberServiceImpl(WeiXinFeign weiXinFeign) {
        this.weiXinFeign = weiXinFeign;
    }

    @Override
    public AppEntity memberInvokeWeixin() {
        return weiXinFeign.getApp();
    }
}
