package cc.wlc.model;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cc.wlc.util.HibernateSessionFactory;

public class SellerTest {

	private Session session = null;

	@Before
	public void setUp() throws Exception {
		session = HibernateSessionFactory.getCurrentSession();
	}

	@After
	public void tearDown() throws Exception {
		session.close();
	}

	@Test
	public void testSeller() {
		String hql = "from Seller";
		Query query = session.createQuery(hql);
		List<Seller> sellers = query.list();
		for (Seller seller : sellers) {
			System.out.println(seller);
		}
	}

	/*
	 * 1.name 2.tel 3.address 4.star
	 */
	@Test
	public void testSelectClauseObjectArray() {
		String hql = "select s.name,s.tel,s.address,s.star from Seller s";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();

		for (Object[] objs : list) {
			System.out.println("===========");
			System.out.println("name:" + objs[0]);
			System.out.println("tel:" + objs[1]);
			System.out.println("address:" + objs[2]);
			System.out.println("star:" + objs[3]);
		}
	}
	
	@Test
	public void testSelectClauseList() {
		String hql = "select new list(s.name,s.tel,s.address) from Seller s";
		Query query = session.createQuery(hql);
		List<List> lists = query.list();
		for(List list:lists) {
			System.out.println("=================");
			System.out.println("name:"+list.get(0));
			System.out.println("tel:"+list.get(1));
			System.out.println("address"+list.get(2));
		}
	}
	
	@Test
	public void testSelectClauseMap() {
		String hql = "select new map(s.name as name,s.tel as tel,s.address as address) from Seller s";
		Query query = session.createQuery(hql);
		List<Map> maps = query.list();
		for(Map map:maps) {
			System.out.println("=================");
			System.out.println("name:"+map.get("name"));
			System.out.println("tel:"+map.get("tel"));
			System.out.println("address:"+map.get("address"));
		}
	}
	
	@Test
	public void testSlectClauseSelf() {
		String hql = "select new Seller(s.name,s.tel,s.address) from Seller s";
		Query query = session.createQuery(hql);
		List<Seller> sellers = query.list();
		for(Seller seller : sellers) {
			System.out.println("=================");
			System.out.println("name:"+seller.getName());
			System.out.println("tel:"+seller.getTel());
			System.out.println("address:"+seller.getAddress());
		}
	}
}
