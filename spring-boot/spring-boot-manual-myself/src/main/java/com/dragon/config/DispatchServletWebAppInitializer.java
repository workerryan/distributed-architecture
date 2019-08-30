package com.dragon.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 加载SpringMvc-Dispatcher-Servlet
 * @author wanglei
 */
public class DispatchServletWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /** 加载根配置信息 spring核心 */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    /** springmvc 加载 配置信息*/
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    /** springmvc 拦截url映射 拦截所有请求*/
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
