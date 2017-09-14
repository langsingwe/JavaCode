package cc.wei.hibernate.day1.test;

import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.junit.Test;

import cc.wei.hibernate.day1.domain.User;
import cc.wei.hibernate.day1.service.IUserDao;
import cc.wei.hibernate.day1.service.UserDaoJdbcImpl;
import junit.framework.Assert;

public class JdbcUserTest {

		private IUserDao dao;
		public JdbcUserTest() {
			dao = new UserDaoJdbcImpl();
		}
		
		@Test
		public void testSave() {
			User u = new User();
			u.setName("xiaoming");
			u.setPassword("123456");
			u.setSex(1);
			u.setBornDate(new Date());
			dao.save(u);
		}
		
		@Test
		public void testGet() {
			User u = dao.get(1l);
			System.out.println(u);
		}
		
		@Test
		public void testUpdate() {
			User u = dao.get(1l);
			u.setName("mike");
			u.setSex(0);
			dao.update(u);
			
			User u2 = dao.get(1l);
			System.out.println(u2);
		}
		
		@Test
		public void testGetAll() {
			List<User> us = dao.getAllUsers();
			for(User u : us) {
				System.out.println(u);
			}
		}
		
		@Test
		public void testGetByName() {
			List<User> us = dao.findUsersByNames("mi");
			for(User u : us) {
				System.out.println(u);
			}
		}
		
		@Test
		public void testDelete() {
			dao.delete(1l);
			User u = dao.get(1l);
			Assert.assertNull(u);
		}
}
