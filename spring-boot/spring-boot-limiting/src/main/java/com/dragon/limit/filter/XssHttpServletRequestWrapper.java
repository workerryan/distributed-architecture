package com.dragon.limit.filter;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 通过覆写HttpServletRequestWrapper，来实现请求参数的修改，从而为XssFilter过滤实现逻辑
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private HttpServletRequest request;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        String parameter = super.getParameter(name);
        System.out.println(name + " = " + parameter);
        if(StringUtils.isNotBlank(parameter)){
            parameter = StringEscapeUtils.escapeHtml4(parameter);
        }
        System.out.println("escape " + parameter);
        return parameter;
    }
}
