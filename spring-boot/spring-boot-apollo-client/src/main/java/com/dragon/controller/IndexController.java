package com.dragon.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
public class IndexController {
    @Value("${jdbc.url:default}")
    private String jdbcUrl;

    @RequestMapping("/jdbcUrl")
    public String getJdbcUrl(){
        return jdbcUrl;
    }
}
