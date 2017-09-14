package cc.wei.hibernate.day1.test;

import java.util.Date;

import org.junit.Test;

import cc.wei.hibernate.day1.domain.User;
import cc.wei.hibernate.day1.service.IUserDao;
import cc.wei.hibernate.day1.service.UserDaoHibernateImpl;

public class HibernateUserTest {
	private IUserDao dao;
	public HibernateUserTest() {
		dao = new UserDaoHibernateImpl();
	}
	@Test
	public void testSave() {
		User u = new User();
		u.setName("≤‚ ‘‘±1");
		u.setPassword("4455");
		u.setSex(3);
		u.setBornDate(new Date());
		dao.save(u);
	}
}
