package com.dragon.member.controller;

import com.dragon.member.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
public class MemberController {
    @Autowired
    private UserFeign userFeign;

    @RequestMapping("/index")
    public String index(){
        return "从member返回了消息，从user返回的消息是 " + userFeign.user();
    }
}
