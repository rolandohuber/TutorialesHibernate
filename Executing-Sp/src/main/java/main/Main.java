package main;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Persona;
import util.HibernateSessionFactory;

public class Main {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		
		/**
		 * sentencia sql para crear la tabla
		 * 
		 *		drop database `test`;
				create database `test`;
				CREATE TABLE `test`.`persona` (
  				`id_persona` INT NOT NULL AUTO_INCREMENT,
  				`nombre_persona` VARCHAR(45) NULL,
  				`apellido_persona` VARCHAR(45) NULL,
  				PRIMARY KEY (`id_persona`),
  				UNIQUE INDEX `id_persona_UNIQUE` (`id_persona` ASC));
		 */
		
		
		/**
		 * ejecutar sql comun y mapearlo a una clase
		 * 
		 * Para consultar un Sp seria de la misma forma, se crea la sentencia sql con la que se ejecutaria y se crea
		 * una clase con todos los datos que trae el resultset
		 * 
		 * Teniendo en cuenta que los nombres de las columnas deben ser exactamente los nombres de
		 * los atributos de la clase que creo
		 * 
		 */

		/*
		 * persona : cuando creo una consulta en ansi sql tiene que ser el
		 * nombre de la tabla(persona)
		 */
		SQLQuery sqlQuery = session.createSQLQuery("select mivariable.* from persona as mivariable");
		/*
		 * le tengo que pasar una entidad con los nombres de los atributos igual
		 * a los nombres de las columnas
		 */
		sqlQuery.addEntity(Persona.class);
		Collection<Persona> list = sqlQuery.list();
		Iterator<Persona> it2 = list.iterator();
		while (it2.hasNext()) {
			Persona pp = it2.next();
			System.out.println(pp.getNombre_persona());
		}

		transaction.commit();
		session.close();

	}

}
