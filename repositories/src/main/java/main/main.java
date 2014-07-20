package main;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import repository.EntityRepository;
import util.HibernateSessionFactory;
import entity.Empleado;
import entity.Persona;

public class main {

	public static void main(String[] args) {

		EntityRepository<Persona> personaRepository = new EntityRepository<Persona>(Persona.class);
		EntityRepository<Empleado> empleadoRepository = new EntityRepository<Empleado>(Empleado.class);

		{
			System.out.println("\n\n ::::::::::::::: Save :::::::::::::::");
			Persona persona = new Persona();
			persona.setLastName("lastName");
			personaRepository.save(persona);
			System.out.println("\n\n ::::::::::::::: Fin Save :::::::::::::::");
		}

		{
			System.out.println("\n\n ::::::::::::::: Update :::::::::::::::");
			Persona persona = personaRepository.get(1L);
			persona.setDni("2222");
			personaRepository.update(persona);
			System.out.println("\n\n ::::::::::::::: FIN Update :::::::::::::::");
		}

		{
			System.out.println("\n\n ::::::::::::::: Merge :::::::::::::::");
			Persona persona = personaRepository.get(1L);
			persona.setTelefone(37572);
			personaRepository.merge(persona);
			System.out.println("\n\n ::::::::::::::: Fin Merge :::::::::::::::");
		}

		{
			System.out.println("\n\n ::::::::::::::: Get :::::::::::::::");
			Persona persona = personaRepository.get(1L);
			personaRepository.delete(persona);
			System.out.println("\n\n ::::::::::::::: fin Get :::::::::::::::");
		}

		{
			System.out.println("\n\n ::::::::::::::: List :::::::::::::::");
			Collection<Persona> personaList = personaRepository.list();
			Iterator<Persona> personaIterator = personaList.iterator();
			while (personaIterator.hasNext()) {
				Persona persona = (Persona) personaIterator.next();
				System.out.println("\n" + persona.getId());
			}
			System.out.println("\n\n ::::::::::::::: fin List :::::::::::::::");
		}

		{
			Empleado empleado = new Empleado();
			empleado.setCargo("Gerente");
			empleadoRepository.save(empleado);
		}

	}

}
