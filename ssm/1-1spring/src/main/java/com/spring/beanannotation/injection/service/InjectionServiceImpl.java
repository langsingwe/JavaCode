package com.spring.beanannotation.injection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.beanannotation.injection.dao.InjectionDao;


@Service
public class InjectionServiceImpl implements InjectionService {
	@Autowired
	private InjectionDao injectionDao;
	
	@Autowired
	public InjectionServiceImpl(InjectionDao injectionDao) {
		this.injectionDao = injectionDao;
	}
	
//	@Autowired
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
