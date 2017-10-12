package cc.wlc.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cc.wlc.entity.Employee;
import cc.wlc.entity.Project;
import cc.wlc.util.HibernateUtil;

/*
 * 多对多关联关系的配置
 * 同时建立了Project到Employee之间的双向多对多的关系
 * 关联关系的维护交由Project方来处理,并且在保存Project对象时会一并保存Employee对象
 */
public class Test {
	public static void main(String[] args){
		Project project1 = new Project(1001,"项目一");
		Project project2 = new Project(1002,"项目二");
		Employee employee1 = new Employee(1,"小明");
		Employee employee2 = new Employee(2,"小红");
		
		//参加项目一的员工有：小明、小红
		project1.getEmployees().add(employee1);
		project1.getEmployees().add(employee2);
		//参加项目二的员工有：小明
		project2.getEmployees().add(employee1);
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(project1);
		session.save(project2);
		tx.commit();
		HibernateUtil.closeSession(session);
	}
}
