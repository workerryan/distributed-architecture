package com.dragon.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wanglei
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
public class ZipkinClientUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinClientUserApplication.class, args);
    }

}
