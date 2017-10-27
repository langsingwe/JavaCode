package test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mybatis.mapper.UserMapper;
import com.mybatis.pojo.Order;
import com.mybatis.pojo.User;

public class Main {
	
	private static ApplicationContext ctx;
	
	static {
		ctx = new ClassPathXmlApplicationContext("config/applicationContext.xml");
	}
	
	public static void main(String[] args) {
		UserMapper userMapper = (UserMapper) ctx.getBean("userMapper");
		//测试id=1的用户查询，可根据数据中的情况修改
		User user = userMapper.getUserById(1);
		System.out.println("获取用户ID=1的用户名："+user.getUsername());
		
		//得到文章列表
		System.out.println("得到用户id为1的所有订单列表:");
		System.out.println("===============================");
		List<Order> orders = userMapper.getUserOrders(1);
		
		for(Order order:orders) {
			System.out.println("订单号：" + order.getOrderNo() + ", 订单金额：" + order.getMoney());
		}
	}
}
