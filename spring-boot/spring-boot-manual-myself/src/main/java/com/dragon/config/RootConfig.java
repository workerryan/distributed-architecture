package com.dragon.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 根配置文件，扫码整个项目
 * @author wanglei
 */
@Configuration
@ComponentScan(value = "com.dragon" )
public class RootConfig {
}
