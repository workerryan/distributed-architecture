package com.dragon;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
@SpringBootApplication
@EnableApolloConfig
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }


    @Value("${wx.msg}")
    private String wxMsg;

    @RequestMapping(value = {"/"})
    public String index(){
        return "wx.msg : " + wxMsg;
    }
}
