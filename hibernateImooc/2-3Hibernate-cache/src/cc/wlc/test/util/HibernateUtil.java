package cc.wlc.test.util;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution.  Follows the Thread Local Session
 * pattern, see {@link http://hibernate.org/42.html}.
 */
public class HibernateUtil {

    private static String CONFIG_FILE_LOCATION = "hibernate.cfg.xml";
	private static final ThreadLocal threadLocal = new ThreadLocal();
    private  static Configuration configuration = new Configuration();
    private static SessionFactory sessionFactory;
    private static String configFile = CONFIG_FILE_LOCATION;

    private HibernateUtil() {
    }
	
    public static Session getCurrentSession() throws HibernateException {
        Session session = (Session) threadLocal.get();

		try {
			if (session == null || !session.isOpen()|| session.connection().isClosed()) {
				if (sessionFactory == null) {
					rebuildSessionFactory();
				}
				session = (sessionFactory != null) ? sessionFactory.openSession()
						: null;
				threadLocal.set(session);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return session;
    }

	/**
     *  Rebuild hibernate session factory
     *
     */
	public static void rebuildSessionFactory() {
		try {
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err
					.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	/**
     *  Close the single hibernate session instance.
     *
     *  @throws HibernateException
     */
    public static void closeCurrentSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

	/**
     *  return session factory
     *
     */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
     *  return session factory
     *
     *	session factory will be rebuilded in the next call
     */
	public static void setConfigFile(String configFile) {
		HibernateUtil.configFile = configFile;
		sessionFactory = null;
	}

	/**
     *  return hibernate configuration
     *
     */
	public static Configuration getConfiguration() {
		return configuration;
	}

}