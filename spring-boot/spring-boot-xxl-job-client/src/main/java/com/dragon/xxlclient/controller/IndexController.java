package com.dragon.xxlclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
public class IndexController {
    @RequestMapping(value = {"/", "/index"})
    public String index(){
        return "yes";
    }
}
