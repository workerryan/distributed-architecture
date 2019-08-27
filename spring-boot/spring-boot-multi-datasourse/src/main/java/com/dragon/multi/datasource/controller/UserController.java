package com.dragon.multi.datasource.controller;

import com.dragon.multi.datasource.entity.User;
import com.dragon.multi.datasource.source1.service.UserSource1Service;
import com.dragon.multi.datasource.source2.service.UserSource2Service;
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

    @Autowired
    private UserSource2Service userSource2Service;


    @RequestMapping("/findByName1")
    public User findByName(String name){
        return userSource1Service.findByName(name);
    }

    @RequestMapping("/insert1")
    public int insert(String name, Integer age){
        return userSource1Service.insert(name, age);
    }

    @RequestMapping("/findByName2")
    public User findByName2(String name){
        return userSource2Service.findByName(name);
    }

    @RequestMapping("/insert2")
    public int insert2(String name, Integer age){
        return userSource2Service.insert(name, age);
    }
}
