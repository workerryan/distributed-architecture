package com.shop.portal.web.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestSessionController {
	@Value("${server.port}")
	private String serverPort;

	/** 创建session 会话*/
	@RequestMapping("/createSession")
	public String createSession(HttpServletRequest request, String nameValue) {
		HttpSession session = request.getSession();
		log.info(">>>>>>>存入Session,SessionId:{},SessionValue:{}", session.getId(), nameValue);
		session.setAttribute("name", nameValue);
		return "success" + ",端口号:" + serverPort;
	}

	/** 获取session 会话*/
	@RequestMapping("/getSession")
	public Object getSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		log.info(">>>>>>>获取Session sessionid:{}", session.getId());
		Object value = session.getAttribute("name");
		return value + ",端口号:" + serverPort;
	}

}
