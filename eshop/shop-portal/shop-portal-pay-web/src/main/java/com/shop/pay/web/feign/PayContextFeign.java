package com.shop.pay.web.feign;

import com.shop.common.core.base.entity.BaseResponse;
import com.shop.common.pay.PayMentTransacDTO;
import com.shop.common.pay.PaymentChannelDTO;
import com.shop.pay.service.PayContextService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient("app-pay")
public interface PayContextFeign extends PayContextService {

    /**
     * 查询所有支付渠道
     *
     * @return
     */
    @GetMapping("/selectAll")
    List<PaymentChannelDTO> selectAll();

    @GetMapping("/tokenByPayMentTransac")
    BaseResponse<PayMentTransacDTO> tokenByPayMentTransac(@RequestParam("token") String token);
}
