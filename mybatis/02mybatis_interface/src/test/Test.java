package test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.test.mybatis.dao.IUser;
import com.test.mybatis.models.User;

/**
 * @ClassName： HelloWorld 
 * @author： weilc
 * @Description：测试类 
 * @date 2017年10月27日 上午9:23:52   
 * 
 */
public class Test {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	
	static {
		try {
			reader = Resources.getResourceAsReader("config/Configure.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			sqlSessionFactory.getConfiguration().addMapper(IUser.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}
	
	public static void main(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUser iuser = session.getMapper(IUser.class);
			User user = iuser.getUserByID(1);
			System.out.println("名字："+user.getName());
			System.out.println("所属部门："+user.getDept());
			System.out.println("主页："+user.getWebsite());
		} finally {
			session.close();
		}
	}
}
