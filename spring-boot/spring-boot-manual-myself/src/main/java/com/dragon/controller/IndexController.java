package com.dragon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/json")
    public String json(){
        return "{\"name\":\"zhangsan\"}";
    }
}
