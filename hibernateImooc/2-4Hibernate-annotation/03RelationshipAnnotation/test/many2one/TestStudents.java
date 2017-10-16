package many2one;

import java.util.EnumSet;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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
}
