package entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Auto {

	private Long id;
	private String patente;
	private Set<Persona> personas;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany(targetEntity = Persona.class, mappedBy = "autos", fetch = FetchType.LAZY)
	public Set<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(Set<Persona> personas) {
		this.personas = personas;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

}
