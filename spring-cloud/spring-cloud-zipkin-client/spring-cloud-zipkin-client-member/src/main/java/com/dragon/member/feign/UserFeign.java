package com.dragon.member.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wanglei
 * @since 1.0.0
 */
@FeignClient(name = "zipkin-client-user")
public interface UserFeign {
    @RequestMapping("/user")
    String user();
}
