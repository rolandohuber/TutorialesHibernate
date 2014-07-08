package entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Persona implements Serializable {
	private static final long serialVersionUID = -3481041518539559833L;

	public static final String TELEFONO_ATTRIBUTE = "telefonos";

	private Long id;
	private String lastName;
	private String dni;
	private List<Telefono> telefonos;

	public Persona() {
		/*
		 * Se inicializa en el contructor ya que si voy a agregarle un telefono
		 * directamente a la lista con el metodo addTelefono lanzara
		 * NullPointerException
		 * 
		 * sino lo que deberia hacer es setearle una lista con el metodo setter
		 */
		telefonos = new LinkedList<Telefono>();
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void addTelefono(Telefono telefono) {
		this.telefonos.add(telefono);
	}

	@OneToMany(fetch = FetchType.LAZY,mappedBy=Telefono.PERSONA_ATTRIBUTE)
	/**
	 * Clase A contiene a Clase B
	 * @OneToMany: con este tag indicamos que la relacion es uno a muchos
	 * 		fetch: indica si va a traer el objeto asociado ya instanciado(EAGER) o si solo traera el id(LAZY), por defecto es EAGER 
	 * 		cascade: es para indicarle que operaciones realiza en cascada
	 * 				CascadeType.ALL: son todas las operaciones, es la que esta por defecto.
	 * 				CascadeType.DETACH: realiza la separacion de los objetos en cascada
	 * 				CascadeType.MERGE: realiza el merge en cascada
	 * 				CascadeType.PERSIST: realiza los insert de los objetos en cascada. o sea si guardo A se guarda B
	 * 				CascadeType.REFRESH: realiza la actualizacion en cascada
	 * 				CascadeType.REMOVE: realiza el delete en cascada
	 * 		mappedBy: es para indicarle el nombre de la variable en mi clase B que representa a A, solo se utiliza en la relacion bidireccional, y tambien nos ayuda a no generar una tabla intermedia 
	 *		orphanRemoval: true/false Es para indicarle que si se elimina el objeto asociado(B) o no, por defecto es false. Lo que quiere decir es 
	 *					   que si yo elimino A tengo no elimina B.
	 *		targetEntity: describe la entidad de destino, generalmente se utiliza en la relacion bidireccional, y tambien nos ayuda a no generar una tabla intermedia
	 *
	 *		Esto generara tres tablas, una para telefono una para Persona y otra para la asociacion
	 *
	 */
	public List<Telefono> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

}
