package com.spring.demo3;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
/*
 * Spring的声明式事务管理的方式二：基于AspectJ的XML方式的配置
 */
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.demo3.service.AccountService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext3.xml")
public class TestDemo3 {
	
	@Resource(name="accountService")
	private AccountService accountService;
	@Test
	/*
	 * 转账案例
	 */
	public void test() {
		accountService.transfer("aaa", "bbb", 200d);
	}
}
