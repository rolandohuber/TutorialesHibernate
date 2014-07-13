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
		telefono3.setNumero(117572);
		telefono3.setOperador("Claro");

		session.save(telefono);
		session.save(telefono3);

		Persona persona = new Persona();
		persona.setDni("315165161");
		persona.setLastName("huber");
		persona.addTelefono(telefono);
		persona.addTelefono(telefono3);

		session.save(persona);

		Persona persona2 = (Persona) session.load(Persona.class, 1L);
		System.out.println("NOMBRE :: " + persona2.getLastName());

		System.out.println("TELEFONO ::: " + persona2.getTelefonos().size());

		transaction.commit();
		session.close();

	}

}
