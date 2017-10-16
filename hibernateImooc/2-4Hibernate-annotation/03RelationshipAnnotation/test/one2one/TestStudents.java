package one2one;

import java.util.Date;
import java.util.EnumSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;

public class TestStudents {
	
	@Test
	public void testSchemaExport() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

		Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();

		SchemaExport schemaExport = new SchemaExport();

		schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
	}
	
	@Test
	public void testAdd() {
		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		IdCard card = new IdCard("123456789132456789","张三丰");
		Students s = new Students(card,"男",new Date(),"太极拳");
		session.save(card);
		session.save(s);
		tx.commit();
	}
}
