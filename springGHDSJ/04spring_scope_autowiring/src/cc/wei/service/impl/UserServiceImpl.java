package cc.wei.service.impl;

import cc.wei.dao.UserDao;
import cc.wei.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	@Override
	public void save() {
		// TODO Auto-generated method stub
		userDao.save();
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
