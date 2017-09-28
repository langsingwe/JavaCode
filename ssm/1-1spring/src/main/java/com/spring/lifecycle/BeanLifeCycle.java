package com.spring.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanLifeCycle implements InitializingBean,DisposableBean{
	/*
	public void start() {
		System.out.println("Bean start");
	}
	public void stop() {
		System.out.println("Bean stop");
	}
	*/
	public void afterPropertiesSet() throws Exception {
		System.out.println("bean afterPropertiesSet");
	}

	public void destroy() throws Exception {
		System.out.println("bean destroy");
	}
}
