package com.shop.zuul;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
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
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }

    /** 添加文档来源，每个服务都要继承swagger，网关相当于是一个收集者 */
    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider {
        @Override
        public List<SwaggerResource> get() {
            List<SwaggerResource> resources = new ArrayList<>();
            resources.add(swaggerResource("app-member", "/app-member/v2/api-docs"));
            resources.add(swaggerResource("app-weixin", "/app-weixin/v2/api-docs"));
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
