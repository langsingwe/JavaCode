package com.test;

import com.service.UserService;
import com.util.ApplicationContextUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		我们先使用传统的方法，来调用UserService的sayHello()方法
//		UserService userService = new UserService();
//		userService.setName("春春");
//		userService.sayHello();
		
		//我们现在用使用spring来完成上面的任务
		//1.得到spring的applicationContext对象（容器对象）
		//ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//UserService us = (UserService) ac.getBean("userService");
		//us.sayHello();
		((UserService)ApplicationContextUtil.getApplicationContext().getBean("userService")).sayHello();
		
		
		//从ac[代表applicationContext容器]中
		//ByeService byeService = (ByeService) ac.getBean("bybService");
		//byeService.sayBye();
	}

}
