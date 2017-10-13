package cc.wlc.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cc.wlc.util.HibernateSessionFactory;

public class CustomerTest {

	private Session session = null;
	
	@Test
	public void testWhere3() {
		String hql = "from Customer c where c.address like '%北京%'";
		Query query = session.createQuery(hql);
		List<Customer> customers = query.list();
		for(Customer c:customers) {
			System.out.println("name:"+c.getName());
			System.out.println("address:"+c.getAddress());
		}
	}
	
	@Test
	public void testWhere2() {
		String hql = "from Customer c where c.age in (20,40)";
		Query query = session.createQuery(hql);
		List<Customer> customers = query.list();
		for(Customer c:customers) {
			System.out.println("name:"+c.getName());
			System.out.println("age:"+c.getAge());
		}
	}
	
	@Test
	public void testDistinct() {
		String hql = "select distinct c.sex from Customer c";
		Query query = session.createQuery(hql);
		List<Object> list = query.list();
		for(Object obj:list) {
			System.out.println(obj);
		}
	}
	
	@Test
	public void testCustomer() {
		String hql = "from Customer";
		Query query = session.createQuery(hql);
		List<Customer> customers = query.list();
		for(Customer customer:customers) {
			System.out.println("name:"+customer.getName());
		}
	}

	@Before
	public void setUp() throws Exception{
		session = HibernateSessionFactory.getCurrentSession();
	}

	@After
	public void tearDown() throws Exception {
		session.close();
	}

}
