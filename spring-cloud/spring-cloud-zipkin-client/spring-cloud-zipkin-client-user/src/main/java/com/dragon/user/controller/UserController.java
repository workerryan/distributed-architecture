package com.dragon.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
public class UserController {
    @RequestMapping("/user")
    public String user(){
        return "从user工程返回了数据";
    }
}
