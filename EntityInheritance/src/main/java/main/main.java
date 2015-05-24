package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.Empleado;
import entity.Persona;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Persona persona1 = new Persona();
		persona1.setNombre("Rolando");
		session.save(persona1);
		
		Persona persona2 = (Persona) session.load(Persona.class, 1L);
		persona2.setNombre("Rolando Huber");
		session.update(persona2);

		Empleado empleado1 = new Empleado();
		empleado1.setCargo("Gerente");
		empleado1.setNombre("Juan");
		empleado1.setTelefono(1154737572);

		session.save(empleado1);

		transaction.commit();
		session.close();

	}

}
