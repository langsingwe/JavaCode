package com.spring.resource;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

public class MyResource implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public void resource() throws IOException {
		//Resource resource = applicationContext.getResource("classpath:config.txt");
		//Resource resource = applicationContext.getResource("file:F:\\eclipse_workspace\\ssm\\01spring\\src\\main\\resources\\config.txt");
		//Resource resource = applicationContext.getResource("url:https://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle");
		Resource resource = applicationContext.getResource("config.txt");
		System.out.println(resource.getFilename());
		System.out.println(resource.contentLength());
	}
}	
