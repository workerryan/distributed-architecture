package com.dragon;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wanglei
 * @since 1.0.0
 */
@SpringBootApplication
@EnableApolloConfig
public class ApolloClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApolloClientApplication.class, args);
    }
}
