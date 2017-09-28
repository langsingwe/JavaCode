package com.spring.beanannotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component("bean")
@Scope
@Component
public class BeanAnnotation {
	public void say(String str) {
		System.out.println("BeanAnnotation:"+str);
	}
	
	public void myHashCode() {
		System.out.println("BeanAnnotation:"+this.hashCode());
	}
}
