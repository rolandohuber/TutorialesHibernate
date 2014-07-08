package Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.Empleado;
import entity.Persona;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Persona pe= new Persona();
		pe.setName("Rolando");
		session.save(pe);
		Persona pepe= (Persona) session.load(Persona.class, 1L);
		pepe.setName("Rolando");
		session.update(pepe);
		
		Empleado empleado=new Empleado();
		empleado.setCargo("Gerente");
		empleado.setName("Juan");
		empleado.setTelefone(1154737572);
		
		session.save(empleado);
		
		transaction.commit();
		session.close();

	}

}
