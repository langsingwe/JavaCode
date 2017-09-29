package com.spring.aop.schema.advice.biz;

public class AspectBiz {
	
	public void biz() {
		System.out.println("AspectBiz biz");
	}
	
	public void init(String bizName, int times) {
		System.out.println("AspectBiz init:" + bizName + " " + times);
	}
}
