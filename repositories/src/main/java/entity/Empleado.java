package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Empleado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6842857646913894464L;

	private Long id;
	private Integer sueldo;
	private String cargo;

	public Empleado() {
	}

	public Integer getSueldo() {
		return sueldo;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
