package com.shop.member.feign;

import com.shop.weixin.service.VerifiedCodeService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "app-weixin")
public interface VerifiedCodeServiceFeign extends VerifiedCodeService {
}
