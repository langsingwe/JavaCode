package com.spring.aop.schema.advice;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {
	
	public void before() {
		System.out.println("MyAspect Before");
	}
	
	public void afterReturning() {
		System.out.println("MyAspect afterReturning");
	}
	
	public void afterThrowing() {
		System.out.println("MyAspect afterThrowing");
	}
	
	public void after() {
		System.out.println("MyAspect after");
	}
	
	public Object around(ProceedingJoinPoint pjp) {
		Object obj = null;
		try {
			System.out.println("MyAspect around 1");
			obj = pjp.proceed();
			System.out.println("MyAspect around 2");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public Object aroundInit(ProceedingJoinPoint pjp,String bizName,int times) {
		System.out.println(bizName + " " + times);
		Object obj = null;
		try {
			System.out.println("MyAspect around 1");
			obj = pjp.proceed();
			System.out.println("MyAspect around 2");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return obj;
	}
}
