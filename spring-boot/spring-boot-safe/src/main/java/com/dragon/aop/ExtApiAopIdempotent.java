package com.dragon.aop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dragon.service.RedisToken;
import com.dragon.utils.ConstantUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ExtApiAopIdempotent {
	@Autowired
	private RedisToken redisToken;

	/** 定义切点表达式*/
	@Pointcut("execution(public * com.dragon.controller.*.*(..))")
	public void rlAop() {
	}

	/** 前置通知*/
	@Before("rlAop()")
	public void before(JoinPoint point) {
		MethodSignature signature = (MethodSignature) point.getSignature();
		ExtApiToken extApiToken = signature.getMethod().getDeclaredAnnotation(ExtApiToken.class);
		if (extApiToken != null) {
			// 可以放入到AOP代码 前置通知
			getRequest().setAttribute("token", redisToken.getToken());
		}
	}

	/** 使用环绕通知来解决幂等性*/
	@Around("rlAop()")
	public Object doBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		// 2.判断方法上是否有加ExtApiIdempotent
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		ExtApiIdempotent declaredAnnotation = methodSignature.getMethod().getDeclaredAnnotation(ExtApiIdempotent.class);
		// 3.如何方法上有加上ExtApiIdempotent
		if (declaredAnnotation != null) {
			String type = declaredAnnotation.type();
			// 如何使用Token 解决幂等性
			// 步骤：
			String token = null;
			//定义一个全局的HttpServletRequest，避免获取多个request对象
			HttpServletRequest request = getRequest();
			if (type.equals(ConstantUtils.EXTAPIHEAD)) {
				token = request.getHeader("token");
			} else {
				token = request.getParameter("token");
			}

			if (StringUtils.isEmpty(token)) {
				return "参数错误";
			}
			// 3.接口获取对应的令牌,如果能够获取该(从redis获取令牌)令牌(将当前令牌删除掉) 就直接执行该访问的业务逻辑
			boolean isToken = redisToken.findToken(token);
			// 4.接口获取对应的令牌,如果获取不到该令牌 直接返回请勿重复提交
			if (!isToken) {
				response("请勿重复提交!");
				// 后面方法不在继续执行
				return null;
			}

		}
		// 放行
		return proceedingJoinPoint.proceed();
	}

	public HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return request;
	}

	public void response(String msg) throws IOException {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		try {
			writer.println(msg);
		} catch (Exception e) {

		} finally {
			writer.close();
		}

	}

}
