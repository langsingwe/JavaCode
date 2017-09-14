package com.hsp.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {	
		//从ApplicationContext中取bean
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/hsp/ioc/beans.xml");
	   
		//获取两个student
		Student s1 = (Student) ac.getBean("student");
		Student s2 = (Student) ac.getBean("student");
		System.out.println(s1 + "\n" + s2);
	}

}
