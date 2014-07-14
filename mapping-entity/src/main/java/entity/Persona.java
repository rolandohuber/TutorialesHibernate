package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(catalog = "", name = "", schema = "", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "dni" }) })
public class Persona implements Serializable {
	
	private static final long serialVersionUID = -3481041518539559833L;

	private Long id;
	private String name;
	private String dni;
	private Integer telefone;
	private Long version;
	private String transientValue;

	public Persona() {
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="nombre")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Version
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getTransientValue() {
		return transientValue;
	}

	public void setTransientValue(String transientValue) {
		this.transientValue = transientValue;
	}

}
