package com.hsp.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/hsp/autowire/beans.xml");
		//获取
		Master master = (Master) ac.getBean("master");
		System.out.println(master.getName() + "养了一只" + master.getDog().getName());
	}
}
