package com.dragon;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wanglei
 * @since 1.0.0
 */
@EnableDubbo
@SpringBootApplication
public class MemberConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberConsumerApplication.class, args);
    }
}
