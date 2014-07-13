package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Persona {

	private Integer id_persona;
	private String nombre_persona;
	private String apellido_persona;

	public Persona() {
	}

	@Id
	@GeneratedValue
	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public String getNombre_persona() {
		return nombre_persona;
	}

	public void setNombre_persona(String nombre_persona) {
		this.nombre_persona = nombre_persona;
	}

	public String getApellido_persona() {
		return apellido_persona;
	}

	public void setApellido_persona(String apellido_persona) {
		this.apellido_persona = apellido_persona;
	}

}
