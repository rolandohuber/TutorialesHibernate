package Main;

import java.util.Iterator;

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
		pee.setName("Rolansdaddo");
		pee.setCasa(casa);
		session.save(pee);
		
		casa.addPersona(pee);
		casa.addPersona(pe);
		
		Persona pepe= (Persona) session.load(Persona.class, 1L);
		pepe.setName("Rolando");
		session.update(pepe);

		Casa casa2= (Casa)session.load(Casa.class, 1l);
		Iterator<Persona> it=casa2.getPersonas().iterator();
		System.out.println("Personas de la casa : Direccion "+casa2.getCalle()+" Altura : "+casa2.getAltura());
		while (it.hasNext()) {
			Persona persona = (Persona) it.next();
			System.out.println("\n     Persona Name : "+persona.getName());
		}
		transaction.commit();
		session.close();

	}

}
