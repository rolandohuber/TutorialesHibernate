package Main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import entity.Curso;
import entity.Estudiante;

public class main {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateSessionFactory.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Estudiante estudiante1 = new Estudiante();
		estudiante1.setName("Estudiante 1");
		session.save(estudiante1);

		Estudiante estudiante2 = new Estudiante();
		estudiante2.setName("Estudiante 2");
		session.save(estudiante2);

		Curso curso = new Curso();
		curso.setNombre("Programacion I");
		session.save(curso);

		curso.addEstudiante(estudiante1);
		curso.addEstudiante(estudiante2);

		session.update(curso);

		estudiante1.addCurso(curso);
		estudiante2.addCurso(curso);

		session.update(estudiante1);
		session.update(estudiante2);

		Curso curso2 = new Curso();
		curso2.setNombre("Programacion II");
		curso2.addEstudiante(estudiante1);
		session.save(curso2);

		estudiante1.addCurso(curso2);
		session.update(estudiante1);

		transaction.commit();
		session.close();
	}
}
