package com.shop.weixin.service.impl;

import com.shop.entity.AppEntity;
import com.shop.weixin.service.WeiXinService;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class WeiXinServiceImpl implements WeiXinService {
    @Override
    public AppEntity getApp() {
        return new AppEntity("dragon", UUID.randomUUID().toString());
    }
}
