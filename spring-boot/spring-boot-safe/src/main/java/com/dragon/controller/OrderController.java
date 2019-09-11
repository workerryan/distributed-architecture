package com.dragon.controller;

import com.dragon.entity.Order;
import com.dragon.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping("token")
    public String getToken(){
        return TokenUtils.getToken();
    }

    @RequestMapping(value="add", produces = "application/json;charset=utf-8")
    public String addOrder(
            @RequestHeader String token,
            @RequestBody Order order){
        if(StringUtils.isBlank(token)){
            return "参数错误";
        }

        if(!TokenUtils.findToken(token)){
            return "请勿重复提交";
        }

        logger.info("添加订单 " + order );
        return "yes";
    }
}
