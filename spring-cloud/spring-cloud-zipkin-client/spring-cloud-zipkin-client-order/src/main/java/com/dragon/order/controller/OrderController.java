package com.dragon.order.controller;

import com.dragon.order.feign.MemberFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
public class OrderController {
    @Autowired
    private MemberFeign memberFeign;

    @RequestMapping("/index")
    public String index(){
        return "从order返回了数据。\n 从member返回的数据是 \n" + memberFeign.index();
    }
}
