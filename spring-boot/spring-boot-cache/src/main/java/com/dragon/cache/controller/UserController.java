package com.dragon.cache.controller;

import com.dragon.cache.entity.Users;
import com.dragon.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/{id}")
    public Users getById(@PathVariable long id){
        return userService.getUser(id);
    }
}
