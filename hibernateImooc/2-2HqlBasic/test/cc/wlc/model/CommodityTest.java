package cc.wlc.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cc.wlc.util.HibernateSessionFactory;

public class CommodityTest {

	private Session session = null;
	
	@Test
	public void testOrderBy() {
		String hql = "from Commodity order by seller.id asc,price desc,name asc";
		Query query = session.createQuery(hql);
		List<Commodity> commodities = query.list();
		for(Commodity c:commodities) {
			System.out.println("name:"+c.getName());
			System.out.println("sellerId:"+c.getSeller().getId());
			System.out.println("sellerName:"+c.getSeller().getName());
			System.out.println("price:"+c.getPrice());
		}
	}
	
	@Test
	public void testWhere4() {
		String hql = "from Commodity c where c.price*5>3000";
		Query query = session.createQuery(hql);
		List<Commodity> commodities = query.list();
		for(Commodity c:commodities) {
			System.out.println("name:"+c.getName());
			System.out.println("price:"+c.getPrice()*5);
		}
	}
	
	@Test
	public void testWhere2() {
		String hql = "from Commodity c where c.price between 100 and 5000 and c.category like '%电脑%'";
		Query query = session.createQuery(hql);
		List<Commodity> commodities = query.list();
		for(Commodity c:commodities) {
			System.out.println("name:"+c.getName());
			System.out.println("category"+c.getCategory());
			System.out.println("price:"+c.getPrice());
		}
	}
	
	@Test
	public void testWhere1() {
		String hql = "from Commodity c where c.description is null";
		Query query = session.createQuery(hql);
		List<Commodity> commodities = query.list();
		for(Commodity c:commodities) {
			System.out.println("name:"+c.getName());
			System.out.println("description:"+c.getDescription());
		}
	}
	
	@Test
	public void testFromClause() {
		String hql = "from Commodity";
		Query query = session.createQuery(hql);
		List<Commodity> commodities = query.list();
		for (Commodity commodity : commodities) {
			System.out.println("name:" + commodity.getName());
//			System.out.println("seller's name:"+commodity.getSeller().getName());
		}
	}

	@Before
	public void setUp() throws Exception {
		session = HibernateSessionFactory.getCurrentSession();
	}

	@After
	public void tearDown() throws Exception {
		session.close();
	}

}
