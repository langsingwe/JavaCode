package com.spring.aware;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.spring.base.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAware extends UnitTestBase{
	public TestAware() {
		super("classpath:spring-aware.xml");
	}
	
//	@Test
//	public void testMyApplicationContext() {
//		System.out.println("testMyApplicationContext:"+super.getBean("myApplicationContext").hashCode());
//	}
	
	@Test
	public void testMyBeanName() {
		System.out.println("testMyBeanName:"+super.getBean("myBeanName"));
	}
}
