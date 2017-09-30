package com.spring.demo1.service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.spring.demo1.dao.AccountDao;
/*
 * 转账案例的业务层实现类
 */
public class AccountServiceImpl implements AccountService {
	//注入转账的DAO
	private AccountDao accountDao;
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	 //注入事务管理模版
    private TransactionTemplate transactionTemplate;
    
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

	/**
	 * @param out ： 转出账号
	 * @param in ： 转入账号
	 * @param money ： 转账金额
	 */
	@Override
    public void transfer(final String out, final String in, final Double money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountDao.outMoney(out, money);
                int i = 1 / 0;
                accountDao.inMoney(in, money);
            }
        });
    }
}
