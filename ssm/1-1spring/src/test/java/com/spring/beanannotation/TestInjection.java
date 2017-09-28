package com.spring.beanannotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.spring.base.UnitTestBase;
import com.spring.beanannotation.injection.service.InjectionService;
import com.spring.beanannotation.multibean.BeanInvoker;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestInjection extends UnitTestBase{
	public TestInjection() {
		super("classpath:spring-beanannotation.xml");
	}
	
	@Test
	public void testAutowired() {
		InjectionService service = super.getBean("injectionServiceImpl");
		service.save("This is autowired.");
	}
	
	@Test
	public void testMultiBean() {
		BeanInvoker invoker = super.getBean("beanInvoker");
		invoker.say();
	}
}
