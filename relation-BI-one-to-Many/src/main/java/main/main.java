package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.Persona;
import entity.Telefono;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Telefono telefono1 = new Telefono();
		telefono1.setNumero(1154737572);
		telefono1.setOperador("Claro");

		Telefono telefono2 = new Telefono();
		telefono2.setNumero(1137572);
		telefono2.setOperador("Clardsfso");

		session.save(telefono1);
		session.save(telefono2);

		Persona persona = new Persona();
		persona.setDocumento("315165161");
		persona.setNombre("huber");
		persona.addTelefono(telefono1);
		persona.addTelefono(telefono2);

		session.save(persona);

		telefono1.setPersona(persona);
		telefono2.setPersona(persona);
		session.update(telefono1);
		session.update(telefono2);

		Telefono telefonoGet = (Telefono) session.load(Telefono.class, 1L);

		System.out.println("Persona :::: " + telefonoGet.getPersona().getNombre());

		transaction.commit();
		session.close();
	}
}
