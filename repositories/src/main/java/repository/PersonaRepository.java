package repository;

import entity.Persona;

public class PersonaRepository extends EntityRepository<Persona> {

	public PersonaRepository() {
		super(Persona.class);
	}

}
