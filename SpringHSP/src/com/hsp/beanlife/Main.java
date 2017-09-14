package com.hsp.beanlife;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/hsp/beanlife/beans.xml");
		PersonService ps = (PersonService) ac.getBean("personService");
		ps.sayHi();
	}

}
