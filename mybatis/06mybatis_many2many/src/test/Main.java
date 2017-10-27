package test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.pojo.UserGroup;

public class Main {

	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	
	static {
		try {
			reader = Resources.getResourceAsReader("config/Configure.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}
	
	public static void testGetGroupAndUsers() {
		UserGroup userGroup = new UserGroup();
		SqlSession session = sqlSessionFactory.openSession();
		try {
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
