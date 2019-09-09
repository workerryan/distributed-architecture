package com.dragon.limit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 使用@ServletComponentScan扫码到Servlet，否则@WebFilter注解可能扫码不到
 */
@ServletComponentScan
@SpringBootApplication
public class LimitApplication {
    public static void main(String[] args) {
        SpringApplication.run(LimitApplication.class, args);
    }
}
