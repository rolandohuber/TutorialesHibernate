package entity;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Empleado extends Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6842857646913894464L;

	private Integer sueldo;
	private String cargo;

	public Empleado() {
	}

	public Integer getSueldo() {
		return sueldo;
	}

	public void setSueldo(Integer sueldo) {
		this.sueldo = sueldo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
