package cc.wlc.test.main;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cc.wlc.test.entity.Employee;
import cc.wlc.test.util.HibernateUtil;

public class Main {
	public static void main(String[] args) {
		Session session = HibernateUtil.getCurrentSession();
		Employee emp = (Employee) session.get(Employee.class, 1);
		System.out.println(emp.getName());
		
		session = HibernateUtil.getCurrentSession();
		emp = (Employee) session.get(Employee.class, 1);
		System.out.println(emp.getName());

		session.close();
	}
}
