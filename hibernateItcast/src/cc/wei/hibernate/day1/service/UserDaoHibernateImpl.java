package cc.wei.hibernate.day1.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cc.wei.hibernate.day1.domain.User;
import cc.wei.hibernate.day1.util.HibernateUtil;

public class UserDaoHibernateImpl implements IUserDao {

	@Override
	public void save(User u) {
		//从会话工厂里得到会话
		Session session = HibernateUtil.getSession();
		//开启一个事务
		Transaction tx = session.beginTransaction();
		//保存对象
		session.save(u);
		//提交事务
		tx.commit();
		//关闭会话
		session.close();
	}

	@Override
	public void delete(Long id) {
	
		//从会话工厂里得到会话
		Session session = HibernateUtil.getSession();
		//开启一个事务
		Transaction tx = session.beginTransaction();
		//删除对象
		User u = new User();
		u.setId(id);
		session.delete(u);
		//提交事务
		tx.commit();
		//关闭会话
		session.close();
	}

	@Override
	public void update(User u) {
		 Session session = HibernateUtil.getSession();
		 Transaction tx = session.beginTransaction();
		 //修改对象
		 session.update(u);
		 tx.commit();
		 session.close();
		
	}

	@Override
	public User get(Long id) {
		Session session = HibernateUtil.getSession();
		User u = (User)session.get(User.class,id);
		session.close();
		return u;
	}

	@Override
	public List<User> getAllUsers() {
		Session session = HibernateUtil.getSession();
		//创建查询器
		Query q = session.createQuery("SELECT u FROM Uer u");
		//查询结果
		List<User> us = q.list();
		return us;
	}

	@Override
	public List<User> findUsersByNames(String name) {
		Session session = HibernateUtil.getSession();
		//创建查询器
		Query q = session.createQuery("SELECT u FROM Uer u WHERE u.name=?");
		q.setString(0, "%"+name+"%");
		//查询结果
		List<User> us = q.list();
		return us;
	}

}
