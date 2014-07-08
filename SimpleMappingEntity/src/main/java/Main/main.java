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
		/*
		 * Si se ejecuta dos veces se puede probar la restriccion de la tabla
		 * La que decia que no podemos tener dos personas con el name iguales
		 * */
		Persona pe= new Persona();
		pe.setName("Rolando");
		session.save(pe);
		Persona pepe= (Persona) session.load(Persona.class, 1L);
		pepe.setName("Rolando");
		session.update(pepe);
		transaction.commit();
		session.close();

	}

}
