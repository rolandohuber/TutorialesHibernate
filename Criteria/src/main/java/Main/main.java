package Main;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import util.HibernateSessionFactory;
import entity.Persona;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

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
		
		
		transaction.commit();
		session.close();
	}
}
