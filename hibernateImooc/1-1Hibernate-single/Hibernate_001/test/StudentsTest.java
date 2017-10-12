import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/*
 * 测试类
 */
public class StudentsTest {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init() {
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建会话工厂
		sessionFactory = config.buildSessionFactory();
		//打开会话
		session = sessionFactory.getCurrentSession();
		//打开事务
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy() {
		//提交事务
		transaction.commit();
		//关闭会话
		session.close();
		//关闭会话工厂
		sessionFactory.close();
	}
	
	@Test
	public void testSaveStudents() {
		
		//生成学生对象
		Students s = new Students(1,"张三丰","男",new Date(),"武当山");
		//保存对象进入数据库s
		session.save(s);
	}
}

