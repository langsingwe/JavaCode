package cc.wei.factory;

import cc.wei.vo.User;

/*
 * 通过动态工厂来创建类
 * @author weiliangchun
 */
public class UserDynamicFactory {
	public User createUser (int age,String name) {
		return new User(age,name);
	}
}
