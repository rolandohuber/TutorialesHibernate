package com.rolando.main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.rolando.entity.Cliente;
import com.rolando.util.HibernateUtil;

public class Principal {
	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionfactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Cliente cliente = new Cliente();
			cliente.setNombre("Juan");
			cliente.setSaldo(100D);
			cliente.setApellido("dc");
			cliente.setCuit("0-0-0");
			cliente.setFechaAlta(new Date());
			cliente.setNombreUsuario("pepe");
			Long id = (Long) session.save(cliente);
			System.out.println("Id generado " + id);

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			HibernateUtil.getSessionfactory().close();
		}
		session.close();
		HibernateUtil.getSessionfactory().close();
	}
	
}
