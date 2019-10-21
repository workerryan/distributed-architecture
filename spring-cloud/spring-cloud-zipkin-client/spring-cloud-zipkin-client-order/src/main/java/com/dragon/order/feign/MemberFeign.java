package com.dragon.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wanglei
 * @since 1.0.0
 */
@FeignClient(name = "zipkin-client-member")
public interface MemberFeign {
    @RequestMapping("/index")
    String index();
}
