import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;
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
//		Students s = new Students(1,"张三丰","男",new Date(),"武当山");
		Students s = new Students();
//		s.setAddress("合肥");
		Address address = new Address("230011","12588881455","北京");
		s.setAddress(address);
		s.setBirthday(new Date());
		s.setGender("女");
		s.setSname("莉莉");
//		s.setSid(1);
		//保存对象进入数据库s
		session.save(s);
	}
	
	@Test
	public void testWriteBlob() throws Exception {
		Students s = new Students(1,"张三丰","男",new Date(),"武当山");
		//先获得照片文件
		File f = new File("d:"+File.separator+"boy.jpg");
		//获得照片文件的输入流
		InputStream input = new FileInputStream(f);
		//创建一个Blob对象
		Blob image = Hibernate.getLobCreator(session).createBlob(input,input.available());
		//设置照片属性
		s.setPicture(image);
		//保存学生
		session.save(s);
	}
	
	@Test
	public void testReadBlob() throws Exception {
		Students s = session.get(Students.class, 1);
		//获得Blob对象
		Blob image = s.getPicture();
		//获得输入流
		InputStream input = image.getBinaryStream();
		//创建输出流
		File f = new File("d:"+File.separator+"dest.jpg");
		//获得输出流
		OutputStream output = new FileOutputStream(f);
		//创建缓冲区
		byte[] buff = new byte[input.available()];
		input.read(buff);
		output.write(buff);
		input.close();
		output.close();
	}
}

