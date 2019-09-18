package com.dragon.oauth2.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dragon.oauth2.**.mapper")
public class SpringBootOauth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOauth2ServerApplication.class, args);
	}

}
