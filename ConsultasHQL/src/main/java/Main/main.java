package Main;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.Query;
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

		/***
		 * Query sin parametros
		 * 
		 * from Persona: se coloca Persona con mayuscula por que se debe indicar
		 * el nombre de la clase
		 * 
		 * Con esta Query se obtiene la lista de todas las personas existentes
		 * en la base de datos
		 * 
		 * */
		{
			String hqlList = "from Persona";
			// creo la query que va a ejecutar hibernate
			Query queryList = session.createQuery(hqlList);
			/**
			 * con el metodo query.list() ejecuto la query y me devuelve una
			 * lista de objetos
			 */
			Collection<Persona> lista = queryList.list();
			Iterator<Persona> it = lista.iterator();
			while (it.hasNext()) {
				Persona p = (Persona) it.next();
				System.out.println(p.getId());
			}
		}
		/***
		 * Query con parametros
		 * 
		 * en esta query lo que hacemos es obtener una persona por id y nombre
		 * 
		 * Persona as p --> lo que quiere decir es que p es un objeto del tipo
		 * Persona y se lo utilizara como alias en la query para comparar sus
		 * atributos con valores que yo les setee
		 * 
		 * p.name --> es la variable de persona que quiero comparar :mivariable
		 * --> es el parametro que le paso para comparar
		 * 
		 * p.id --> es la variable de persona que quiero comparar :id --> es el
		 * parametro que le paso para comparar
		 * 
		 */
		{
			String hql = "from Persona as p where p.name=:mivariable and p.id=:id";
			Query query = session.createQuery(hql);
			/**
			 * con query.setParameter(String nombreVariable,Object value) asigno
			 * valores a las variables
			 * 
			 * query.setParameter("mivariable", "pepe"); quiere decir que la
			 * variable :mivariable va a tener el valor pepe con lo cual va a
			 * buscar personas con p.name=pepe
			 * 
			 */
			query.setParameter("mivariable", "pepe"); //
			query.setParameter("id", 5L);
			Collection<Persona> lista = query.list();
			Iterator<Persona> it = lista.iterator();
			while (it.hasNext()) {
				Persona p = (Persona) it.next();
				System.out.println(p.getName());
			}
		}
		/***
		 * Query con parametros y unico resultado
		 */
		{
			String hql = "from Persona as p where p.id=:id";
			Query query = session.createQuery(hql);

			/**
			 * query.uniqueResult();
			 * 
			 * lo que me dice es que esa query va a traer un unico resultado, en
			 * el caso que aya mas ??
			 * 
			 */
			query.setParameter("id", 5L);
			Persona p = (Persona) query.uniqueResult();
			System.out.println(p.getName());
		}
		/***
		 * Query traer direccion de una persona
		 */
		{
			/*
			 * String hql =
			 * "select p.direccion from Persona as p where p.id=:id"; Query
			 * query = session.createQuery(hql);
			 * 
			 * // asigno variables: query.setParameter("id", 8L);
			 * 
			 * query.setParameter("id", 1L);
			 * 
			 * Direccion p = (Direccion) query.uniqueResult();
			 * System.out.println("P.Direccion.Calle ::" + p.getCalle() +
			 * " P.Direccion.Altura ::" + p.getAltura());
			 */
		}
		{
			/**
			 * Consulta de atributos que son objetos
			 * 
			 * esta query lo que hace es traernos el telefono de la persona que
			 * posee un cierto id
			 */
			String hql = "select p.telefonos from Persona as p where p.id=:id";
			Query query = session.createQuery(hql);
			query.setParameter("id", 1L);

			Collection<Telefono> p = query.list();
			Iterator<Telefono> it = p.iterator();
			while (it.hasNext()) {
				Telefono pp = (Telefono) it.next();
				System.out.println(pp.getNumber());
			}
		}
		/*
		 * // * Paginacion con hql String hql2 = "from Persona"; Query query2 =
		 * session.createQuery(hql2); // * seteo desde que valor debe traer
		 * query2.setFirstResult(1); // * seteo cuantos valores debe traer
		 * query2.setMaxResults(2);
		 * 
		 * Collection<Persona> list = query2.list(); Iterator<Persona> it2 =
		 * list.iterator(); while (it2.hasNext()) { Persona pp = it2.next();
		 * System.out.println(pp.getName()); }
		 */

		/**
		 * ejecutar sql comun y mapearlo a una clase
		 */
		/*
		 * // personas : cuando creo una consulta en ansi sql tiene que ser el
		 * nombre de la tabla(persona) SQLQuery sqlQuery =
		 * session.createSQLQuery("select P.* from persona as P"); //le tengo
		 * que pasar una entidad con los nombres de los atributos igual a los
		 * nombres de las columnas sqlQuery.addEntity(Persona.class);
		 * Collection<Persona> list = sqlQuery.list(); Iterator<Persona> it2 =
		 * list.iterator(); while (it2.hasNext()) { Persona pp = it2.next();
		 * System.out.println(pp.getName()); }
		 */

		/**
		 * querys varias
		 */

		/**
		 * obtener datos especificos de una persona por ejemplo el nombre
		 */
		/*
		 * String hql = "select p.name from Persona as p where p.id=:id"; Query
		 * query = session.createQuery(hql); query.setParameter("id", 1L);
		 * 
		 * String nombre = (String) query.uniqueResult();
		 * 
		 * System.out.println("NOMBRE :: "+nombre);
		 */

		/**
		 * obtener datos especificos de una persona por ejemplo nombre y id esto
		 * devuelve una lista de verctores de Object
		 */
		/*
		 * hql="select p.name, p.id  from Persona as p"; query =
		 * session.createQuery(hql);
		 * 
		 * Collection<Object[]> list= query.list(); Iterator<Object[]>
		 * it=list.iterator(); while(it.hasNext()){ Object[] ppe= it.next();
		 * System.out.println(ppe[0]+" :::: "+ppe[1]); }
		 */

		/**
		 * trae una lista de persoinas que posean esa misma direccion
		 */
		// String hql="from Persona as p where p.direccion.calle= :calle";
		/**
		 * realizar un LIKE
		 */
		// String hql="from Persona as p where p.direccion.calle like :bla";

		/**
		 * obtiene todas las personas donde el nombre no sea nulo
		 */

		// String hql="from Persona as p where p.name is not null";

		/**
		 * obtiene todas las personas donde el nombre sea alguno de los que
		 * estan dentro de los parentecis
		 */
		// String
		// hql="from Persona as p where p.name in('lknvsdl','hdhfhf','vdfvf')";
		/**
		 * order by
		 */
		// String
		// hql="from Persona as p where p.name in('lknvsdl','forro',sueldo) order by p.name"
		// ;

		/*
		 * Query query = session.createQuery(hql); query.setParameter("id", 1l);
		 * 
		 * Collection<Persona> list= query.list(); Iterator<Persona>
		 * it=list.iterator(); while(it.hasNext()){ Persona ppe= it.next();
		 * System.out.println(ppe.getName()+" :::: "+ppe.getId()); }
		 */

		/**
		 * obtengo la patente de un auto que pertenece a un usuario, tengo que
		 * hacer los join o no funciona
		 */
		/*
		 * String hql =
		 * "select a.patente from Persona as p join p.autos a where p.id=:id";
		 * 
		 * 
		 * Query query = session.createQuery(hql); query.setParameter("id", 1l);
		 * 
		 * Collection<String> list = query.list(); Iterator<String> it =
		 * list.iterator(); while (it.hasNext()) { String ppe = it.next();
		 * System.out.println(ppe); }
		 */

		/**
		 * select sum(e.salario) from Empleado as e where id=:id select
		 * avg(e.salario) from Empleado as e where id=:id select count(*) from
		 * Empleado as e where id=:id select min(e.salario) from Empleado as e
		 * where id=:id select max(e.salario) from Empleado as e where id=:id
		 * 
		 */

		String hqlCount = "select count(*) from Persona";

		Query queryhqlCount = session.createQuery(hqlCount);

		Long count = (Long) queryhqlCount.uniqueResult();
		System.out.println(count);

		transaction.commit();
		session.close();

	}
}
