package main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.Persona;
import entity.Persona.Example;

public class Main {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Persona persona = new Persona();
		persona.setNombre("Juan");
		persona.setDocumento("126464546");
		persona.setTelefono(1154737572);
		persona.setTransientValue("Im transient");
		persona.setFechaNacimiento(new Date());
		persona.setExample(Example.ONE);

		session.save(persona);

		transaction.commit();
		session.close();
	}

}
