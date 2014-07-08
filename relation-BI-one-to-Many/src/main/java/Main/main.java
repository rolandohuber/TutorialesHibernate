package Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Persona;
import entity.Telefono;
import util.HibernateSessionFactory;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Telefono telefono = new Telefono();
		telefono.setNumero(1154737572);
		telefono.setOperador("Claro");

		Telefono telefono3 = new Telefono();
		telefono3.setNumero(1154737572);
		telefono3.setOperador("Claro");

		session.save(telefono);
		session.save(telefono3);

		Persona persona = new Persona();
		persona.setDni("315165161");
		persona.setLastName("huber");
		persona.addTelefono(telefono);
		persona.addTelefono(telefono3);

		session.save(persona);

		telefono.setPersona(persona);
		telefono3.setPersona(persona);
		session.update(telefono);
		session.update(telefono3);

		Telefono telefono2 = (Telefono) session.load(Telefono.class, 1L);

		System.out.println("Persona :::: " + telefono2.getPersona().getLastName());

		transaction.commit();
		session.close();

	}

}
