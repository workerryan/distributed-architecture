package com.dragon.limit.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 使用一个拦截器拦截所有请求，将特殊的参数进行转换
 */
@WebFilter(filterName = "xssFilter", urlPatterns = "/*")
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        XssHttpServletRequestWrapper wrapper = new XssHttpServletRequestWrapper(req);
        chain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {

    }
}
