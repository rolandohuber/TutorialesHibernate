package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Persona {

	public static final String ID_ATTRIBUTE = "id";
	public static final String NOMBRE_ATTRIBUTE = "nombre";
	public static final String SUELDO_ATTRIBUTE = "sueldo";
	public static final String DIRECCION_ATTRIBUTE = "direccion";
	public static final String TELEFONOS_ATTRIBUTE = "telefonos";
	public static final String AUTOS_ATTRIBUTE = "autos";

	private Long id;
	private String nombre;
	private Long sueldo;
	private Direccion direccion;
	private List<Telefono> telefonos = new ArrayList<Telefono>();
	private List<Auto> autos = new ArrayList<Auto>();

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return nombre;
	}

	public void setName(String name) {
		this.nombre = name;
	}

	public Long getSueldo() {
		return sueldo;
	}

	public void setSueldo(Long sueldo) {
		this.sueldo = sueldo;
	}

	@ManyToOne
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@OneToMany
	@JoinColumn(name = "id_persona")
	public List<Telefono> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	@ManyToMany
	public List<Auto> getAutos() {
		return autos;
	}

	public void setAutos(List<Auto> autos) {
		this.autos = autos;
	}
}