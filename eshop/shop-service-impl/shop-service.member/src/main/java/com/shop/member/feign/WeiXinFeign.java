package com.shop.member.feign;

import com.shop.weixin.service.WeiXinService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "app-weixin")
public interface WeiXinFeign extends WeiXinService {
}
