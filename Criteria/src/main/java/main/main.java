package main;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.HibernateSessionFactory;
import entity.Auto;
import entity.Direccion;
import entity.Persona;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
	/*	{
			Direccion direccion = new Direccion();
			direccion.setAltura(1500L);
			direccion.setCalle("Mexico");
			Long id = (Long) session.save(direccion);
			direccion.setId(id);

			Auto auto = new Auto();
			auto.setPatente("CLF147");
			session.save(auto);

			Auto auto2 = new Auto();
			auto2.setPatente("CLF147");
			session.save(auto2);

			Persona persona = new Persona();
			persona.setName("Rolando");
			persona.setSueldo(1500L);
			persona.setDireccion(direccion);
			List<Auto> autos = new LinkedList<Auto>();
			autos.add(auto);
			autos.add(auto2);
			persona.setAutos(autos);
			
			Set<Persona> personas = new HashSet<Persona>();
			personas.add(persona);
			auto.setPersonas(personas);
			session.update(auto);
			auto2.setPersonas(personas);
			session.update(auto2);

			session.save(persona);

		}*/
		/**
		 * criteria basico: lista todos las personas
		 */

		{
			Criteria criteria = session.createCriteria(Persona.class);
			Collection<Persona> list = criteria.list();

			Iterator<Persona> it = list.iterator();
			while (it.hasNext()) {
				Persona persona = (Persona) it.next();
				System.out.println(persona.getId() + " ::: " + persona.getName());
			}
		}
		/**
		 * Restricciones sobre atributos de la persona
		 */
		{
			Criteria criteria = session.createCriteria(Persona.class);

			// //mayor que
			// criteria.add(Restrictions.gt("id", 2000));
			// //menor que
			// criteria.add(Restrictions.lt("id", 2000));
			// //mayor o igual
			// criteria.add(Restrictions.lt("id", 2000));
			// //like
			// criteria.add(Restrictions.like("name", "%a%"));
			// //incasesensitive like
			// criteria.add(Restrictions.ilike("name", "%A%"));
			// //entre
			// criteria.add(Restrictions.between("sueldo", 2000,1000));
			// //is null
			// criteria.add(Restrictions.isNull("sueldo"));
			// //isNotNull
			criteria.add(Restrictions.isNotNull("sueldo"));
			Collection<Persona> list = criteria.list();

			Iterator<Persona> it = list.iterator();
			while (it.hasNext()) {
				Persona persona = (Persona) it.next();
				System.out.println(persona.getName());
			}
		}

		/**
		 * Criteria de Criteria
		 */
		{
			Criteria criteriaAutos = session.createCriteria(Auto.class);
			
			Criteria criteriaPersona=criteriaAutos.createCriteria("personas");
			
			Criteria criteriaDireccion=criteriaPersona.createCriteria("direccion");
			criteriaDireccion.add(Restrictions.eq("id", 1L));
			
			Collection<Auto> list = criteriaAutos.list();
			Iterator<Auto> it = list.iterator();
			while (it.hasNext()) {
				Auto auto = (Auto) it.next();
				System.out.println(auto.getId() + " :::: " + auto.getPatente());
			}
		}
		transaction.commit();
		session.close();
	}
}
