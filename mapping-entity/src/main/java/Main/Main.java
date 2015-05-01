package Main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.Persona;
import entity.Persona.Example;

public class Main {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Persona pe = new Persona();
		pe.setName("Juan");
		pe.setDni("126464546");
		pe.setTelefone(1154737572);
		pe.setTransientValue("Im transient");
		pe.setFecha_nacim(new Date());
		pe.setExample(Example.ONE);

		session.save(pe);

		transaction.commit();
		session.close();
	}

}
