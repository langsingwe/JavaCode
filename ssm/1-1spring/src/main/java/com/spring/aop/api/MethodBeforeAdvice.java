package com.spring.aop.api;

import java.lang.reflect.Method;

public class MethodBeforeAdvice implements org.springframework.aop.MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("MethodBeforeAdvice: " + method.getName() + " " + target.getClass().getName());
	}

}
