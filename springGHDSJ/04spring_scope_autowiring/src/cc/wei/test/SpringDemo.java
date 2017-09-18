package cc.wei.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cc.wei.service.UserService;
import cc.wei.vo.Role;

public class SpringDemo {
	public static void main(String[] args) {
		//读取配置文件，启动容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
		//根据标识符获取对象
		UserService userService = (UserService) ac.getBean("userService");
		userService.save();
	}
}
