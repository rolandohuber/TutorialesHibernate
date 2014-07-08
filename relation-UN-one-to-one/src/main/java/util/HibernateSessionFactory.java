package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateSessionFactory {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	/*
	 * Crea el sessionfactory con el patron singleton
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			// crea el session factory desde el hibernate.cfg.xml que lo toma
			// del classpath
			return new AnnotationConfiguration().configure().buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}

}
