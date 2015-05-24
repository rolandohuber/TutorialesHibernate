package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionFactory {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	/*
	 * Crea el sessionfactory con el patron singleton
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			// crea el session factory desde el hibernate.cfg.xml que lo toma
			// del classpath
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			// apply configuration property settings to
			// StandardServiceRegistryBuilder
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			return sessionFactory;

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
