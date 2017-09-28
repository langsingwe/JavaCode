package com.spring.ioc.injection.service;

import com.spring.ioc.injection.dao.InjectionDao;

public class InjectionServiceImpl implements InjectionService {
	private InjectionDao injectionDao;
	
	//构造器注入
	public InjectionServiceImpl(InjectionDao injectionDao) {
		this.injectionDao = injectionDao;
	}

	//设值注入
	public void setInjectionDao(InjectionDao injectionDao) {
		this.injectionDao = injectionDao;
	}

	public void save(String str) {
		//模拟业务操作
		System.out.println("Service接收参数:" + str);
		str = str + ":" + this.hashCode();
		injectionDao.save(str);
	}

}
