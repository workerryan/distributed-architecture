package com.dragon.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wanglei
 * @since 1.0.0
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ZipkinClientOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinClientOrderApplication.class, args);
    }
}
