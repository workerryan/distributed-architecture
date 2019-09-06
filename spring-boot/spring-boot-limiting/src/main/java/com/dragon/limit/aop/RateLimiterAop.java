package com.dragon.limit.aop;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 功能说明:使用AOP环绕通知判断拦截所有springmvc 请求，判断请求方法上是否存在ExtRateLimiter <br>
 * 1.判断请求方法上是否有@ExtRateLimiter<br>
 * 2.如果方法上存在@ExtRateLimiter注解话<br>
 * 3.使用反射技术获取@ExtRateLimiter注解方法上的参数<br>
 * 4.调用原生RateLimiter代码创建令牌桶<br>
 * 5.如果获取令牌超时的，直接调用服务降级方法（需要自己定义）<br>
 * 6.如果能够获取令牌的话，直接进入实际请求方法。<br>
 * AOP创建方式有两种 注解版本和XML方式<br>
 */
@Aspect
@Component
public class RateLimiterAop {
	private Map<String, RateLimiter> rateHashMap = new ConcurrentHashMap<>();

	/** 定义切入点*/
	@Pointcut("execution(public * com.dragon.limit.*.*(..))")
	public void rlAop() {
	}
 	/** 使用AOP环绕通知判断拦截所有springmvc 请求，判断请求方法上是否存在ExtRateLimiter注解*/
	@Around("rlAop()")
	public Object doBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// 1.如果请求方法上存在@ExtRateLimiter注解的话
		Method signatureMethod = getSignatureMethod(proceedingJoinPoint);
		if (signatureMethod == null) {
			// 直接报错
			return null;
		}
		// 2.使用java的反射机制获取拦截方法上自定义注解的参数
		ExtRateLimiter extRateLimiter = signatureMethod.getDeclaredAnnotation(ExtRateLimiter.class);
		if (extRateLimiter == null) {
			// 直接进入实际请求方法中
			return proceedingJoinPoint.proceed();
		}
		double permitsPerSecond = extRateLimiter.permitsPerSecond();
		long timeout = extRateLimiter.timeout();
		// 3.调用原生的RateLimiter创建令牌 保证每个请求对应都是单例的RateLimiter
		// 在这里使用hashMap， key为 请求的url地址，将相同的请求在同一个桶
		String requestURI = getRequestURI();
		RateLimiter rateLimiter = null;
		if (rateHashMap.containsKey(requestURI)) {
			// 如果在hashMap URL 能检测到RateLimiter
			rateLimiter = rateHashMap.get(requestURI);
		} else {
			// 如果在hashMap URL 没有检测到RateLimiter 添加新的RateLimiter
			rateLimiter = RateLimiter.create(permitsPerSecond);
			rateHashMap.put(requestURI, rateLimiter);
		}
		// 4.获取令牌桶中的令牌，如果没有有效期获取到令牌的话，则直接调用本地服务降级方法，不会进入到实际请求方法中。
		boolean tryAcquire = rateLimiter.tryAcquire(timeout, TimeUnit.MILLISECONDS);
		if (!tryAcquire) {
			// 服务降级
			fallback();
			return null;
		}
		// 5.获取令牌桶中的令牌，如果能在有效期获取令牌到令的话，则直接进入到实际请求方法中。
		// 直接进入实际请求方法中
		return proceedingJoinPoint.proceed();
	}

	private void fallback() throws IOException {
		System.out.println("服务降级别抢了， 在抢也是一直等待的， 还是放弃吧！！！");
		// 在AOP编程中获取响应
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		try {
			writer.println("别抢了， 在抢也是一直等待的， 还是放弃吧！！！");
		} catch (Exception e) {

		} finally {
			writer.close();

		}

	}

	private String getRequestURI() {
		return getRequest().getRequestURI();
	}

	private HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attributes.getRequest();
	}

	/** 获取到AOP拦截的方法*/
	private Method getSignatureMethod(ProceedingJoinPoint proceedingJoinPoint) {
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		// 获取到AOP拦截的方法
		return signature.getMethod();
	}

}
