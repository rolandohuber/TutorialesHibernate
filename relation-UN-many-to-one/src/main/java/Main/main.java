package Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Casa;
import entity.Persona;
import util.HibernateSessionFactory;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Casa casa=new Casa();
		casa.setCalle("PEPE");
		casa.setAltura(1270);
		session.save(casa);
		
		Persona pe= new Persona();
		pe.setName("Rolando");
		pe.setCasa(casa);
		session.save(pe);
		
		Persona pee= new Persona();
		pee.setName("Rolando");
		pee.setCasa(casa);
		session.save(pee);
		
		
		Persona pepe= (Persona) session.load(Persona.class, 1L);
		pepe.setName("Rolando");
		session.update(pepe);
		
		transaction.commit();
		session.close();

	}

}
