package com.spring.autowiring;

public class AutoWiringService {
	
	private AutoWiringDao autoWiringDao;

	
	public AutoWiringService(AutoWiringDao autoWiringDao) {
		System.out.println("AutoWiringService");
		this.autoWiringDao = autoWiringDao;
	}

	public void setAutoWiringDao(AutoWiringDao autoWiringDao) {
		this.autoWiringDao = autoWiringDao;
	}

	public void say(String str) {
		this.autoWiringDao.say(str);
	}
}
