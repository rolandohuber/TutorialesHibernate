package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Casa {
	private Long id;
	private String calle;
	private Integer altura;
	private Set<Persona> personas;

	public Casa() {
		personas = new HashSet<Persona>();
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	@OneToMany(targetEntity = Persona.class, mappedBy = Persona.CASA_ATTRIBUTE)
	public Set<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(Set<Persona> personas) {
		this.personas = personas;
	}

	public void addPersona(Persona persona) {
		this.personas.add(persona);
	}

}
