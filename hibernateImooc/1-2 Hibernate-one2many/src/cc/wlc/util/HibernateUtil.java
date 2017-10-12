package cc.wlc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static Session session;
	
	static {
		Configuration config = new Configuration().configure();
		sessionFactory = config.buildSessionFactory();
	}
	
	//获取sessionFactory
	public static SessionFactory geSessionFactory() {
		return sessionFactory;
	}
	
	//获取session
	public static Session getSession() {
		session = sessionFactory.openSession();
		return session;
	}
	
	//关闭session
	public static void closeSession(Session session) {
		if(session!=null) {
			session.close();
		}
	}
}
