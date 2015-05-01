package entity;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Persona extends entity.Entity implements Serializable {
	private static final long serialVersionUID = -3481041518539559833L;

	private String apellido;
	private String documento;
	private Integer telefono;

	public Persona() {
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

}
