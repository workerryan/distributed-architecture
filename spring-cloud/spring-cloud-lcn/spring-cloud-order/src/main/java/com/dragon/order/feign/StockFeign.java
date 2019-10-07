package com.dragon.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wanglei
 * @since 1.0.0
 */
@FeignClient(name = "stock")
public interface StockFeign {

    @RequestMapping("/descStock")
    boolean descStock(@RequestParam("skuId") Integer skuId);
}
