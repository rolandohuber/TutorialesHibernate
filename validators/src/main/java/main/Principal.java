package main;

import java.util.Date;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entity.Cliente;

public class Principal {

	public static void main(String[] args) {
		/**
		 * 	Cuando utilizamos anotaciones de validaciones de Hibernate
		 * cada operacion de persistencia debemos envolverla en un try-catch.
		 * 
		 * 	Cuando no cumple con una validacion devuelve una excepcion del tipo
		 * ConstraintViolationException que posee una lista de ContraintViolations
		 * y cada ContraintViolation posee el nombre del atributo y el mensaje de 
		 * error de la validacion
		 */
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Cliente cliente = new Cliente();
			cliente.setNombre("Ju");
			cliente.setApellido("dc");
			cliente.setCuit("0-0-0");
			cliente.setFechaAlta(new Date());
			cliente.setNombreUsuario("fsdfs");
			Long id = (Long) session.save(cliente);
			System.out.println("Id generado " + id);

			transaction.commit();
		} catch (ConstraintViolationException e) {

			for (ConstraintViolation<?> c : e.getConstraintViolations()) {
				System.out.println(c.getPropertyPath() + " " + c.getMessage());

			}
			transaction.rollback();
		}
		session.close();
		HibernateUtil.getSessionFactory().close();
	}

}
