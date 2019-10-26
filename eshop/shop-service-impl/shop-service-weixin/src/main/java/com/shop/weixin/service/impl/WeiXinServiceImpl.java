package com.shop.weixin.service.impl;

import com.shop.entity.AppEntity;
import com.shop.weixin.service.WeiXinService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeiXinServiceImpl implements WeiXinService {

    @Value("${weixin.self.value}")
    private String selfValue;

    @Override
    public AppEntity getApp() {
        return new AppEntity("dragon", selfValue);
    }
}
