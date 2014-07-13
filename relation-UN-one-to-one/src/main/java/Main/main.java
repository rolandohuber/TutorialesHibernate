package Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.Persona;
import entity.Telefono;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Telefono telefono= new Telefono();
		telefono.setNumero(1154737572);
		telefono.setOperador("Claro");
		
		session.save(telefono);
		
		Persona persona = new Persona();
		persona.setDni("315165161");
		persona.setLastName("huber");
		persona.setTelefono(telefono);
		
		session.save(persona);
		
		Persona pepe= (Persona) session.load(Persona.class, 5L);
		System.out.println("NOMBRE :: "+pepe.getLastName());
		
		System.out.println("TELEFONO ::: "+pepe.getTelefono().getNumero());
		//session.delete(pepe);
		transaction.commit();
		session.close();

	}

}
