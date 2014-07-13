package Main;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Auto;
import entity.Direccion;
import entity.Persona;
import entity.Telefono;
import util.HibernateSessionFactory;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
	/*
	 * {
		Transaction transaction2 = session.beginTransaction();
		for(int i=0;i<5;++i){
			Direccion direccion= new Direccion();
			direccion.setCalle("Roca"+i);
			session.save(direccion);
		}
		for(int i=0;i<5;++i){
			Auto auto= new Auto();
			auto.setPatente("SFSF"+i);
			session.save(auto);
		}
		for(int i=0;i<5;++i){
			Telefono telefono= new Telefono();
			telefono.setNumber("1155485");
			session.save(telefono);
		}
		for(int i=0;i<10;++i){
			Persona p= new Persona();
			p.setName("Roland");
			session.save(p);
		}
		
		Persona ppp=(Persona)session.load(Persona.class, 1L);
		Query queryList = session.createQuery("from Auto");
		ppp.setAutos(queryList.list());
		session.update(ppp);
		
		Persona pp=(Persona)session.load(Persona.class, 2L);
		Query queryList2 = session.createQuery("from Telefono");
		pp.setTelefonos(queryList2.list());
		session.update(pp);
		
		Persona p=(Persona)session.load(Persona.class, 3L);
		Direccion d=(Direccion)session.load(Direccion.class, 1L);
		p.setDireccion(d);
		session.update(p);
		
		transaction2.commit();
	}*/
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
			System.out.println(hqlList);
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
		 * 					Persona y se lo utilizara como alias en la query para comparar sus
		 * 					atributos con valores que yo les setee
		 * 
		 * p.name --> es la variable de persona que quiero comparar 
		 * :mivariable --> es el parametro que le paso para comparar
		 * 
		 * p.id --> es la variable de persona que quiero comparar 
		 * :id --> es el parametro que le paso para comparar
		 * 
		 */
		{
			String hql = "from Persona as p where p.name=:mivariable and p.id=:id";
			System.out.println("\n\n\n"+hql);
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
			query.setParameter("mivariable", "Roland"); 
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
			System.out.println("\n\n\n"+hql);
			Query query = session.createQuery(hql);

			/**
			 * query.uniqueResult();
			 * 
			 * lo que me dice es que esa query va a traer un unico resultado, en
			 * el caso que aya mas devuelve org.hibernate.NonUniqueResultException
			 * 
			 */
			query.setParameter("id", 1L);
			Persona p = (Persona) query.uniqueResult();
			System.out.println(p.getName());
		}
		/***
		 * Query con parametros pidiendo unico resultado pero existen mas
		 */
		{
			String hql = "from Persona as p where p.name=:name";
			System.out.println("\n\n\n"+hql);
			Query query = session.createQuery(hql);

			/**
			 * query.uniqueResult();
			 * 
			 * lo que me dice es que esa query va a traer un unico resultado, en
			 * el caso que aya mas devuelve org.hibernate.NonUniqueResultException
			 * 
			 */
			query.setParameter("name", "Roland");
			try{
				Persona p = (Persona) query.uniqueResult();
				System.out.println(p.getName());
			}catch(org.hibernate.NonUniqueResultException e){
				System.out.println("Existen mas de un resultado para la query");
			}
		}
		/***
		 * Query traer direccion de una persona
		 */
		{
			
			 String hql = "select p.direccion from Persona as p where p.id=:id";
			 System.out.println("\n\n\n"+hql);
			 Query query = session.createQuery(hql);
			 query.setParameter("id", 3L);

			 Direccion p = (Direccion) query.uniqueResult();
			 System.out.println("P.Direccion.Calle ::" + p.getCalle() + " P.Direccion.Altura ::" + p.getAltura());
			
		}
		
		/* Paginacion con hql */
		{
			String hql = "from Persona"; 
			 System.out.println("\n\n\n"+hql);
			//obtengo los primeros dos
			 Query query2 =session.createQuery(hql); // * seteo desde que valor debe traer
			query2.setFirstResult(1); // * seteo cuantos valores debe traer
			query2.setMaxResults(2);
			 
			Collection<Persona> list = query2.list(); 
			Iterator<Persona> it2 = list.iterator(); 
			while (it2.hasNext()) { 
			 Persona pp = it2.next();
		     System.out.println(pp.getId()+" "+pp.getName()); 
		     }
			
			//obtengo los siguientes cinco
			query2 =session.createQuery(hql); 
			
			// * seteo desde que valor debe traer
			query2.setFirstResult(2); 
			// * seteo cuantos valores debe traer
			query2.setMaxResults(5);
			 
			list = query2.list(); 
			it2 = list.iterator(); 
			while (it2.hasNext()) { 
			 Persona pp = it2.next();
			 System.out.println(pp.getId()+" "+pp.getName());
		     }
		}

		
		/**
		 * querys varias
		 */

		/**
		 * obtener datos especificos de una persona por ejemplo el nombre
		 */
		{
		  String hql = "select p.name from Persona as p where p.id=:id"; 
		  System.out.println("\n\n\n"+hql);
		  Query query = session.createQuery(hql); 
		  query.setParameter("id", 1L);
		  
		  String nombre = (String) query.uniqueResult();
		  
		  System.out.println("NOMBRE :: "+nombre);
		}

		/**
		 * obtener datos especificos de una persona por ejemplo nombre y id esto
		 * devuelve una lista de verctores de Object
		 */
		{
			String hql="select p.name, p.id  from Persona as p";
			System.out.println("\n\n\n"+hql);
			Query query = session.createQuery(hql);
			 
			 Collection<Object[]> list= query.list(); 
			 Iterator<Object[]> it=list.iterator(); 
			 while(it.hasNext()){ 
				 Object[] ppe= it.next();
				 System.out.println(ppe[0]+" :::: "+ppe[1]); 
			}
		}

		/**
		 * trae una lista de personas que posean esa misma direccion
		 */
		{	
			String hql="from Persona as p where p.direccion.calle= :calle";
			System.out.println("\n\n\n"+hql);
			Query query=session.createQuery(hql);
			query.setParameter("calle", "Roca0");
			Collection<Persona> list= query.list(); 
			 Iterator<Persona> it=list.iterator(); 
			 while(it.hasNext()){ 
				 Persona p= it.next();
				 System.out.println(p.getId()); 
			}
		}
		/**
		 * realizar un LIKE
		 */
		{	
			String hql="from Direccion as d where d.calle like :bla";
			System.out.println("\n\n\n"+hql);
			Query query=session.createQuery(hql);
			query.setParameter("bla", "%oca%");
			Collection<Direccion> list= query.list(); 
			 Iterator<Direccion> it=list.iterator(); 
			 while(it.hasNext()){ 
				 Direccion p= it.next();
				 System.out.println(p.getId()); 
			}
		}
		/**
		 * obtiene todas las personas donde la direccion no sea nulo
		 */

		{	
			String hql="from Persona as p where p.direccion is not null";
			System.out.println("\n\n\n"+hql);
			Query query=session.createQuery(hql);
			Collection<Persona> list= query.list(); 
			 Iterator<Persona> it=list.iterator(); 
			 while(it.hasNext()){ 
				 Persona p= it.next();
				 System.out.println(p.getId()+" :::::: "+p.getName()); 
			}
		}
		
		/**
		 * obtiene todas las personas donde la altura de la direccion sea nulo
		 */

		{	
			String hql="from Persona as p where p.direccion.altura is null";
			System.out.println("\n\n\n"+hql);
			Query query=session.createQuery(hql);
			Collection<Persona> list= query.list(); 
			 Iterator<Persona> it=list.iterator(); 
			 while(it.hasNext()){ 
				 Persona p= it.next();
				 System.out.println(p.getId()+" :::::: "+p.getName()); 
			}
		}
		
		/**
		 * obtiene todas las personas donde el nombre sea alguno de los que
		 * estan dentro de los parentecis
		 */

		{	
			String hql="from Persona as p where p.name in('Juan','carlos','luis')";
			System.out.println("\n\n\n"+hql);
			Query query=session.createQuery(hql);
			Collection<Persona> list= query.list(); 
			 Iterator<Persona> it=list.iterator(); 
			 while(it.hasNext()){ 
				 Persona p= it.next();
				 System.out.println(p.getId()+" :::::: "+p.getName()); 
			}
		}
		
		/**
		 * order by
		 */

		{	
			String hql="from Persona as p where p.name in('Juan','carlos','luis') order by p.name";
			System.out.println("\n\n\n"+hql);
			Query query=session.createQuery(hql);
			Collection<Persona> list= query.list(); 
			 Iterator<Persona> it=list.iterator(); 
			 while(it.hasNext()){ 
				 Persona p= it.next();
				 System.out.println(p.getId()+" :::::: "+p.getName()); 
			}
		}

		/**
		 * obtengo la patente de un auto que pertenece a un usuario, tengo que
		 * hacer los join o no funciona
		 */
		{
			 String hql = "select a.patente from Persona as p join p.autos a where p.id=:id";
			 System.out.println("\n\n\n"+hql);
			 Query query = session.createQuery(hql); 
			 query.setParameter("id", 1l);
			 Collection<String> list = query.list(); 
			 Iterator<String> it = list.iterator(); 
			 while (it.hasNext()) 
			 { 
				 String ppe = it.next();
				 System.out.println(ppe); 
			 }
		}

		/**
		 * obtenemos el valor del sueldo mas alto entre las personas
		 */
		{
			String hql = "select max(p.sueldo) from Persona as p";
			System.out.println("\n\n\n"+hql);	
			Query queryhqlCount = session.createQuery(hql);
			Long count = (Long) queryhqlCount.uniqueResult();
			System.out.println(count);
		}
		
		/**
		 * obtenemos el valor del sueldo mas bajo entre las personas
		 */
		{
			String hql = "select min(p.sueldo) from Persona as p";
			System.out.println("\n\n\n"+hql);	
			Query queryhqlCount = session.createQuery(hql);
			Long count = (Long) queryhqlCount.uniqueResult();
			System.out.println(count);
		}
		
		/**
		 * obtenemos el promedio de la suma de los sueldos de las personas
		 */
		{
			String hql = "select avg(p.sueldo) from Persona as p";
			System.out.println("\n\n\n"+hql);	
			Query queryhqlCount = session.createQuery(hql);
			Double count = (Double) queryhqlCount.uniqueResult();
			System.out.println(count);
		}
		
		/**
		 * obtengo la suma de todos los sueldos de las personas
		 */
		{
			String hql = "select sum(p.sueldo) from Persona as p";
			System.out.println("\n\n\n"+hql);	
			Query queryhqlCount = session.createQuery(hql);
			Long count = (Long) queryhqlCount.uniqueResult();
			System.out.println(count);
		}
		
		
		/**
		 *	otengo cuantas personas existen en la base de datos 
		 */
		{
			String hql = "select count(*) from Persona";
			System.out.println("\n\n\n"+hql);	
			Query queryhqlCount = session.createQuery(hql);
			Long count = (Long) queryhqlCount.uniqueResult();
			System.out.println(count);
		}
		
		transaction.commit();
		session.close();

	}
}
