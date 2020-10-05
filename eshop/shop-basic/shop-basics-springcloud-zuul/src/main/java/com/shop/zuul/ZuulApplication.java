package com.shop.zuul;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 网关入口
 * @author wanglei
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableSwagger2Doc
@EnableApolloConfig
@EnableHystrix
public class ZuulApplication {
    /** 获取到的ApolloConfig */
    @ApolloConfig
    private Config appConfig;

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }


    /** 添加文档来源，每个服务都要继承swagger，网关相当于是一个收集者，
     *  当页面访问swagger-ui的时候，就会进入该代码，
     *  当配置发生变化的时候，就会进入事件监听器，事件监听器会继续调用get()方法，get()方法会调用resources()从而实现了动态配置*/
    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider {
        @Override
        public List<SwaggerResource> get() {
            // 开启监听，配置文件发生改变需要更改，如果发生了改变，那么就继续调用该方法
            appConfig.addChangeListener(new ConfigChangeListener() {
                @Override
                public void onChange(ConfigChangeEvent changeEvent) {
                    get();
                }
            });
            return resources();
        }


        /**
         * 从阿波罗服务器中获取resources
         */
        private List<SwaggerResource> resources() {
            List<SwaggerResource> resources = new ArrayList<>();
            // 网关使用服务别名获取远程服务的SwaggerApi，当没有获取到zuu.swagger.document的时候，默认值为空字符串
            String swaggerDocJson = appConfig.getProperty("zuu.swagger.document", "");
            JSONArray jsonArray = JSONArray.parseArray(swaggerDocJson);
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                String name = jsonObject.getString("name");
                String location = jsonObject.getString("location");
                resources.add(swaggerResource(name, location));
            }
            return resources;
        }


        /**
         * 添加服务的文档地址
         * @param name      服务的注册名称
         * @param location  文档地址，第一部分是接口的地址
         */
        private SwaggerResource  swaggerResource(String name, String location) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion("2.0");
            return swaggerResource;
        }
    }
}
