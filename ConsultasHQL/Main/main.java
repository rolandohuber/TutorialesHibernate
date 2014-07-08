package Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Persona;
import util.HibernateSessionFactory;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(new Persona());
		transaction.commit();
		session.close();

	}

}
