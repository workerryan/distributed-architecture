package com.dragon.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wanglei
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ZipkinClientMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinClientMemberApplication.class, args);
    }
}
