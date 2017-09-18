package cc.wei.factory;
/*
 * 创建User 静态工厂
 * @author weiliangchun
 */

import cc.wei.vo.User;

public class UserStaticFactory {
	public static User createUser(int age,String name) {
		return new User(age,name);
	}
}	
