package com.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyApplicationContext implements ApplicationContextAware{

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("MyApplicationContext:"+applicationContext.getBean("myApplicationContext").hashCode());
	}
}
