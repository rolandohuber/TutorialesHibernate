package entity;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Persona extends entity.Entity implements Serializable {
	private static final long serialVersionUID = -3481041518539559833L;

	private String lastName;
	private String dni;
	private Integer telefone;

	public Persona() {
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

}
