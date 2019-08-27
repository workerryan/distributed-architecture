package com.dragon.springboot.jta;

import com.dragon.springboot.jta.properties.DBProperties1;
import com.dragon.springboot.jta.properties.DBProperties2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(value = { DBProperties1.class, DBProperties2.class })
public class JtaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JtaApplication.class, args);
    }
}
