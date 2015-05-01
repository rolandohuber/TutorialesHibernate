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

		Telefono telefono1 = new Telefono();
		telefono1.setNumero(1154737572);
		telefono1.setOperador("Claro");
		
		Telefono telefono2 = new Telefono();
		telefono2.setNumero(117572);
		telefono2.setOperador("Claro");

		session.save(telefono1);
		session.save(telefono2);

		Persona persona = new Persona();
		persona.setDocumento("315165161");
		persona.setNombre("huber");
		persona.addTelefono(telefono1);
		persona.addTelefono(telefono2);

		session.save(persona);

		Persona persona2 = (Persona) session.load(Persona.class, 1L);
		System.out.println("NOMBRE :: " + persona2.getNombre());

		System.out.println("TELEFONO ::: " + persona2.getTelefonos().size());

		transaction.commit();
		session.close();

	}

}
