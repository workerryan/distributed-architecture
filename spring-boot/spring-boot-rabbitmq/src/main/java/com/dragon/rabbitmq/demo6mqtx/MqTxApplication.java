package com.dragon.rabbitmq.demo6mqtx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于MQ的分布式事务，案例就是订单服务调用派单服务
 * @author wanglei
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan("com.dragon.rabbitmq.demo6mqtx.**.mapper")
public class MqTxApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqTxApplication.class, args);
    }
}
