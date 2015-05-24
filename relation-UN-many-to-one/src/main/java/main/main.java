package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Casa;
import entity.Persona;
import util.HibernateSessionFactory;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Casa casa=new Casa();
		casa.setCalle("Calle 1");
		casa.setAltura(1270);
		session.save(casa);
		
		Persona perez= new Persona();
		perez.setApellido("Perez");
		perez.setCasa(casa);
		session.save(perez);
		
		Persona gomez= new Persona();
		gomez.setApellido("Gomez");
		gomez.setCasa(casa);
		session.save(gomez);
		
		
		Persona garcia= (Persona) session.load(Persona.class, 1L);
		garcia.setApellido("Garcia");
		session.update(garcia);
		
		transaction.commit();
		session.close();

	}

}
