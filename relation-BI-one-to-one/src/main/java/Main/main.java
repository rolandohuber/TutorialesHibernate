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
		/*
		 * En la relacion bidireccional los dos objetos relacionados se conocen
		 */
		Telefono telefono = new Telefono();
		telefono.setNumero(1154737572);
		telefono.setOperador("Claro");

		session.save(telefono);

		Persona persona = new Persona();
		persona.setDocumento("315165161");
		persona.setNombre("huber");
		persona.setTelefono(telefono);

		telefono.setPersona(persona);

		session.save(persona);
		session.update(telefono);

		Persona persona2 = (Persona) session.load(Persona.class, 1L);
		System.out.println("NOMBRE :: " + persona2.getNombre());

		System.out.println("TELEFONO ::: " + persona2.getTelefono().getNumero());

		Telefono telefono2 = (Telefono) session.load(Telefono.class, 1L);
		System.out.println("PROPIETARIO TELEFONO ::: " + telefono2.getPersona().getNombre());

		transaction.commit();
		session.close();

	}

}
