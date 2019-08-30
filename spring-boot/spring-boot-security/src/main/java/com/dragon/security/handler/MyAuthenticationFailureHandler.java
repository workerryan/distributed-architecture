package com.dragon.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 认证失败的Handler
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException auth)
			throws IOException, ServletException {
		System.out.println("登陆失败!");
		res.sendRedirect("/login");
	}

}
