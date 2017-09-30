package com.spring.demo4.service;

import org.springframework.transaction.annotation.Transactional;

import com.spring.demo4.dao.AccountDao;
/*
 * 转账案例的业务层实现类
 */

@Transactional
public class AccountServiceImpl implements AccountService {
	//注入转账的DAO
	private AccountDao accountDao;
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	/**
	 * @param out ： 转出账号
	 * @param in ： 转入账号
	 * @param money ： 转账金额
	 */
	@Override
	public void transfer(String out, String in, Double money) {
		accountDao.outMoney(out, money);
		int i = 1 / 0;
		accountDao.inMoney(in, money);
	}
}
