package repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;

public class EntityRepository<T extends Serializable> {

	private SessionFactory sessionFactory;
	/**
	 * En esta clase creamos todos los metodos que son genericos para todos los objetos que se persisten.
	 * En el caso de que necesitemos implementar algun metodo en particular lo que podemos hacer es extender de esta clase
	 * e implementar los metodos que deseemos.
	 * 
	 * Para hacer la clase generica debemos utilizar generics e indicarle el tipo de clase que es al momento de instanciarla,
	 * como se ve en la clase main.
	 */

	private Class<?> genericClass;

	public EntityRepository(Class<?> genericClass) {
		sessionFactory = HibernateSessionFactory.getSessionfactory();
		this.genericClass = genericClass;
	}

	public Long save(T entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Long id = (Long) session.save(entity);
		transaction.commit();
		session.close();
		return id;
	}

	public void update(T entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
	}

	public void delete(T entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(entity);
		transaction.commit();
		session.close();
	}

	public T get(Long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		T entity = (T) session.get(genericClass, id);
		transaction.commit();
		session.close();
		return entity;
	}

	public Collection<T> list() {
		Session session = sessionFactory.openSession();
		List<T> list;
		Query query = session.createQuery("from " + genericClass.getCanonicalName());
		list = query.list();
		session.close();
		return list;
	}

	public void merge(T entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.merge(entity);
		transaction.commit();
		session.close();
	}
}
