import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class SessionTest {

	@Test
	public void testOpenSession() {
		Configuration config = new Configuration().configure();//获得配置对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		
		System.out.println(session1==session2); //false
//		if(session!=null) {
//			System.out.println("session创建成功！");
//		}
//		else {
//			System.out.println("session创建失败！");
//		}
	}
	
	@Test
	public void testGetCurrentSession() {
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建会话工厂
		SessionFactory sessionFactory = config.buildSessionFactory();
		//打开会话
		Session session1 = sessionFactory.getCurrentSession();
		Session session2 = sessionFactory.getCurrentSession();
		System.out.println(session1==session2); //true
		
//		if(session!=null) {
//			System.out.println("session创建成功!");
//		}
//		else {
//			System.out.println("session创建失败！");
//		}
	}
	
	@Test
	public void testSaveStudentsWithOpenSession() {
		//获得配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		//创建会话工厂对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//打开会话
		Session session1 = sessionFactory.openSession();
		//开启事务
		Transaction tx = session1.beginTransaction();
		//生成学生对象
		Students s = new Students(1,"张三","男",new Date(),"北京");
		session1.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("connection hashcode1: " + connection.hashCode());
			}
		});
		session1.save(s);
		session1.close();
		tx.commit();//提交事务
		
		Session session2 = sessionFactory.openSession();;
		tx = session2.beginTransaction();
		s = new Students(2,"李四","男",new Date(),"安徽");
		session2.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("connection hashcode2: " + connection.hashCode());
			}
		});
		session2.save(s);
		tx.commit();
	}
	
	@Test
	public void testSaveStudentsWithGetCurrentSession() {
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建会话工厂
		SessionFactory sessionFactory = config.buildSessionFactory();
		//打开会话
		Session session1 = sessionFactory.getCurrentSession();
		//打开事务
		Transaction tx = session1.beginTransaction();
		//生成学生对象
		Students s = new Students(1,"张三","男",new Date(),"北京");
		session1.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("connection hashcode1: " + connection.hashCode());
			}
		});
		session1.save(s);
		//session1.close();
		tx.commit();//提交事务
		
		Session session2 = sessionFactory.openSession();;
		tx = session2.beginTransaction();
		s = new Students(2,"李四","男",new Date(),"安徽");
		session2.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("connection hashcode2: " + connection.hashCode());
			}
		});
		session2.save(s);
		tx.commit();
	}
}
