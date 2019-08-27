package com.dragon.springboot.jta.controller;

import com.dragon.springboot.jta.source1.service.UserSource1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
public class UserController {

    @Autowired
    private UserSource1Service userSource1Service;

    @RequestMapping("/insert")
    public int insert(String name, Integer age){
        return userSource1Service.insert(name, age);
    }
}
