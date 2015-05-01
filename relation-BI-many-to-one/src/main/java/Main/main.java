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
		casa.setCalle("Calle 1 ");
		casa.setAltura(1270);
		session.save(casa);
		
		Persona persona1= new Persona();
		persona1.setNombre("Persona 1");
		persona1.setCasa(casa);
		session.save(persona1);
		
		Persona persona2= new Persona();
		persona2.setNombre("Persona 2");
		persona2.setCasa(casa);
		session.save(persona2);
		
		casa.addPersona(persona2);
		casa.addPersona(persona1);
		
		Persona persona1GET= (Persona) session.load(Persona.class, 1L);
		persona1GET.setNombre("Rolando");
		session.update(persona1GET);

		Casa casa2= (Casa)session.load(Casa.class, 1l);
		Iterator<Persona> it=casa2.getPersonas().iterator();
		System.out.println("Personas de la casa : Direccion "+casa2.getCalle()+" Altura : "+casa2.getAltura());
		while (it.hasNext()) {
			Persona persona = (Persona) it.next();
			System.out.println("\n     Persona Nombre : "+persona.getNombre());
		}
		transaction.commit();
		session.close();

	}

}
