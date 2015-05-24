package com.rolando.main;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.rolando.entity.Curso;
import com.rolando.entity.Estudiante;
import com.rolando.util.HibernateUtil;

public class Principal {
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {

		{ // CREA LA BD
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();

			Estudiante estudiante1 = new Estudiante();
			estudiante1.setNombre("Estudiante 1");
			session.save(estudiante1);

			Estudiante estudiante2 = new Estudiante();
			estudiante2.setNombre("Estudiante 2");
			estudiante2.setApellido("apellido2");
			session.save(estudiante2);

			Curso curso = new Curso();
			curso.setPrioridad(1);
			curso.setFechaCreacion(new Date(2015, 05, 23));
			curso.setNombre("Programacion I");
			session.save(curso);

			Curso curso4 = new Curso();
			curso4.setPrioridad(4);
			curso4.setFechaCreacion(new Date(2015, 05, 22));
			curso4.setNombre("IIII");
			session.save(curso4);

			Curso curso3 = new Curso();
			curso3.setPrioridad(3);
			curso3.setFechaCreacion(new Date(2015, 05, 21));
			curso3.setNombre("III");
			session.save(curso3);

			curso.addEstudiante(estudiante1);
			curso.addEstudiante(estudiante2);

			curso3.addEstudiante(estudiante2);
			session.update(curso3);
			session.update(curso);

			estudiante1.addCurso(curso);
			estudiante2.addCurso(curso);
			estudiante2.addCurso(curso3);

			session.update(estudiante1);
			session.update(estudiante2);

			Curso curso2 = new Curso();
			curso2.setPrioridad(2);
			curso2.setFechaCreacion(new Date(2015, 05, 24));
			curso2.setNombre("Programacion II");
			curso2.setDescripcion("es una descripcion de prueba");
			curso2.addEstudiante(estudiante1);
			session.save(curso2);

			estudiante1.addCurso(curso2);
			session.update(estudiante1);

			transaction.commit();
			session.close();
		}
		/* Indexa los registros */

		{
			try {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				FullTextSession fullTextSession = Search.getFullTextSession(session);
				fullTextSession.createIndexer().startAndWait();
			} catch (InterruptedException e) { // TODO Auto-generated catch
												// block
				e.printStackTrace();
			}
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		QueryBuilder qb = null;
		FullTextSession fullTextSession = null;
		org.apache.lucene.search.Query query = null;
		org.hibernate.Query hibQuery = null;

		/**
		 * Utilizaremos qb.keyword() que es para buscar una palabra especifica
		 * */

		{
			fullTextSession = Search.getFullTextSession(session);
			qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Estudiante.class).get();
			System.out.println("Busqueda sobre un atributo en particular ------------------------------------------------");
			query = qb.keyword().onFields(Estudiante.NOMBRE_ATTRIBUTE).matching("Estudiante").createQuery();
			hibQuery = fullTextSession.createFullTextQuery(query, Estudiante.class);
			List<Estudiante> result = hibQuery.list();
			for (Estudiante c : result) {
				System.out.println(c.getNombre());
			}
		}
		{
			fullTextSession = Search.getFullTextSession(session);
			qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Estudiante.class).get();
			System.out.println("Busqueda sobre mas de un atributo ------------------------------------------------");
			query = qb.keyword().onFields(Estudiante.NOMBRE_ATTRIBUTE, Estudiante.APELLIDO_ATTRIBUTE).matching("Estudiante").createQuery();
			hibQuery = fullTextSession.createFullTextQuery(query, Estudiante.class);
			List<Estudiante> result = hibQuery.list();
			for (Estudiante c : result) {
				System.out.println(c.getNombre());
			}
		}

		{
			System.out.println("Busca por fonetica por ejemplo programacion y programasion tienen la misma fonetica, con lo cual van a coincidir ------------------------------------------------");
			qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Curso.class).get();
			query = qb.keyword().fuzzy().onField(Curso.NAME_ATTRIBUTE).matching("programasion").createQuery();
			hibQuery = fullTextSession.createFullTextQuery(query, Curso.class);
			List<Curso> result2 = hibQuery.list();
			for (Curso c : result2) {
				System.out.println(c.getNombre());
			}
		}
		{
			System.out.println("Busca por contenido en la palabra seria equivalente al like *texto* en sql------------------------------------------------");
			qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Estudiante.class).get();
			query = qb.keyword().wildcard().onField(Estudiante.NOMBRE_ATTRIBUTE).matching("*studi*").createQuery();
			hibQuery = fullTextSession.createFullTextQuery(query, Estudiante.class);
			List<Estudiante> result = hibQuery.list();
			for (Estudiante c : result) {
				System.out.println(c.getNombre());
			}
		}
		{
			System.out.println("Busca por frase en un campo------------------------------------------------");
			qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Curso.class).get();
			query = qb.phrase().onField(Curso.DESCRIPCION_ATTRIBUTE).sentence("una descripcion").createQuery();
			hibQuery = fullTextSession.createFullTextQuery(query, Curso.class);
			List<Curso> result = hibQuery.list();
			for (Curso c : result) {
				System.out.println(c.getDescripcion());
			}
		}
		{
			System.out.println("Busca por rango en un campo------------------------------------------------");
			qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Curso.class).get();
			query = qb.range().onField(Curso.PRIORIDAD_ATTRIBUTE).from(2).to(3).createQuery();
			hibQuery = fullTextSession.createFullTextQuery(query, Curso.class);
			List<Curso> result = hibQuery.list();
			for (Curso c : result) {
				System.out.println(c.getNombre());
			}
		}
		{
			System.out.println("Busca por rango en un campo tipo fecha------------------------------------------------");
			qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Curso.class).get();
			// hasta tal fecha
			query = qb.range().onField(Curso.FECHA_CREACION_ATTRIBUTE).below(new Date(2015, 05, 22)).excludeLimit().createQuery();
			// desde tal fecha
			// query = qb.range().onField(Curso.DATE_ATTRIBUTE).above(new
			// Date(2015, 05, 22)).excludeLimit().createQuery();
			hibQuery = fullTextSession.createFullTextQuery(query, Curso.class);
			List<Curso> result = hibQuery.list();
			for (Curso c : result) {
				System.out.println(c.getNombre());
			}
		}
		{
			System.out.println("Busqueda por Objeto Asociado------------------------------------------------");
			qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Estudiante.class).get();
			query = qb.keyword().onField(Estudiante.CURSOS_ATTRIBUTE + "." + Curso.NAME_ATTRIBUTE).matching("III").createQuery();
			hibQuery = fullTextSession.createFullTextQuery(query);

			List<Estudiante> result3 = hibQuery.list();
			for (Estudiante o : result3) {
				System.out.println(o.getNombre());
			}
		}
		fullTextSession.close();
		HibernateUtil.getSessionFactory().close();
	}
}