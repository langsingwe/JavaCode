package cc.wei.hibernate.day1.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
	private static SessionFactory sf;
	public HibernateUtil() {}
	
	static {
		//启动hibernate，让hibernate读入配置
		//创建hibernate的配置对象
		Configuration conf = new Configuration();
		conf.configure();
		//创建一个SessionFactory,会话工厂
		sf = conf.buildSessionFactory();
		
	}
	public static SessionFactory getSF() {
		return sf;
	}
	public static Session getSession() {
		return sf.openSession();
	}
	
}
