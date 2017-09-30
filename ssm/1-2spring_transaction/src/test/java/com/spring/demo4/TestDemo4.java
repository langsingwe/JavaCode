package com.spring.demo4;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.demo4.service.AccountService;

/*
 * Spring声明式事务管理的方式三：基于注解的事务管理的方式 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext4.xml")
public class TestDemo4 {
	
	@Resource(name="accountService")
	private AccountService accountService;
	
	@Test
	/*
	 * 转账案例 
	 */
	public void test() {
		accountService.transfer("aaa", "bbb", 200d);	}
}
