package Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Curso;
import entity.Estudiante;
import util.HibernateSessionFactory;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Estudiante estudiante = new Estudiante();
		estudiante.setName("Rolando");
		session.save(estudiante);

		Estudiante estudiante2 = new Estudiante();
		estudiante2.setName("Rolando2");
		session.save(estudiante2);

		Curso curso = new Curso();
		curso.setNombre("Programacion I");
		session.save(curso);

		curso.addEstudiante(estudiante);
		curso.addEstudiante(estudiante2);

		session.update(curso);

		estudiante.addCurso(curso);
		estudiante2.addCurso(curso);

		session.update(estudiante);
		session.update(estudiante2);

		Curso curso2 = new Curso();
		curso2.setNombre("Programacion I");
		session.save(curso2);

		transaction.commit();
		session.close();
	}
}
