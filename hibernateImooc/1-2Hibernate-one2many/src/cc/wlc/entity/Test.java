package cc.wlc.entity;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cc.wlc.util.HibernateUtil;
/*
 * 单向一对多关系（班级-->学生）
 * 建立关联关系后，可以方便的从一个对象导航到另一个对象
 * 注意关联的方向
 */
public class Test {
	public static void main(String[] args) {
//		add();
//		findStudentsByGrade();
//		update();
		delete();
	}
	
	//将学生添加到班级
	public static void add() {
		Grade grade = new Grade("java一班","java软件开发一班");
		Student s1 = new Student("张三","男");
		Student s2 = new Student("莉莉", "女");
		
		//如果希望在学生表中添加对应的班级编号，需要在班级中添加学生，建立关联关系
		grade.getStudents().add(s1);
		grade.getStudents().add(s2);
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(grade);
		session.save(s1);
		session.save(s2);
		tx.commit();
		HibernateUtil.closeSession(session);
	}
	
	//查询班级中包含的学生
	public static void findStudentsByGrade() {
		Session session = HibernateUtil.getSession();
		Grade grade = session.get(Grade.class, 1);
		System.out.println(grade.getGname()+","+grade.getGdesc());
		
		Set<Student> students = grade.getStudents();
		for(Student stu:students) {
			System.out.println(stu.getSname()+","+stu.getSex());
		}
	}
	
	//修改学生信息
	public static void update() {

		Grade grade = new Grade("java二班","java软件开发二班"); 
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Student stu = session.get(Student.class, 1);
		grade.getStudents().add(stu);
		session.save(grade);
		tx.commit();
		HibernateUtil.closeSession(session);
	}
	
	//删除学生信息
	public static void delete() {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Student stu = session.get(Student.class, 2);
		session.delete(stu);
		tx.commit();
		HibernateUtil.closeSession(session);
	}
}
