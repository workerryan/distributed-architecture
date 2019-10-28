package com.shop.zuul;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动监听器
 * @author wanglei
 */
@Slf4j
//@Component
public class SwaggerCommandLineRunner implements CommandLineRunner {
    @ApolloConfig
    private Config appConfig;

    @Override
    public void run(String... args) throws Exception {
        // 开启监听，配置文件发生改变需要更改，如果发生了改变，那么就继续调用该方法
        appConfig.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                log.info("修改了配置 " +  changeEvent.changedKeys().toString());
            }
        });
    }
}
