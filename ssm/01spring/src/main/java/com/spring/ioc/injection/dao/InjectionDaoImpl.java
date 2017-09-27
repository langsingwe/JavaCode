package com.spring.ioc.injection.dao;

public class InjectionDaoImpl implements InjectionDao {

	public void save(String str) {
		//模拟数据库保存操作
		System.out.println("保存数据:" + str);
	}

}
