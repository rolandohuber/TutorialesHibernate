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

//esta anotacion es para decirle que se va a persistir
@Entity
public class Persona {
	private Long id;
	private String name;
	private Long sueldo;
	private Direccion direccion;
	private List<Telefono> telefonos= new ArrayList<Telefono>();
	private List<Auto> autos= new ArrayList<Auto>();
	

	// establece que va a ser mi primary key
	@Id
	// es la forma en la que quiero que me genere el id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	//con el join evito la tabla intermedia y guarda el id de la persona por ejemplo
	@JoinColumn(name="id_persona")
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
