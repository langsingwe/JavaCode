package com.spring.demo2;
/*
 * spring的声明式事务管理的方式一的测试类
 */


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.demo2.service.AccountService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class TestDemo2 {
	/*
	 * 注入代理类:因为代理类进行增强的操作
	 */
	
//	@Resource(name="accountService")
	@Resource(name="accountServiceProxy")
	private AccountService accountService;
	@Test
	/*
	 * 转账案例
	 */
	public void test1() {
		accountService.transfer("aaa", "bbb", 200d);
	}
}
