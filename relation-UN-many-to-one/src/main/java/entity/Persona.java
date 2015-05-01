package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Persona implements Serializable {
	private static final long serialVersionUID = -3481041518539559833L;

	private Long id;
	private String nombre;
	private String documento;
	private String apellido;
	private Casa casa;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	/**
	 * @ManyToOne: este tag se utiliza para indicar que la relacion va a ser many-to-one con la entidad casa
	 * 		fetch: indica si va a traer el objeto asociado ya instanciado(EAGER) o si solo traera el id(LAZY), por defecto es EAGER 
	 *		cascade: es para indicarle que operaciones realiza en cascada
	 * 				CascadeType.ALL: son todas las operaciones, es la que esta por defecto.
	 * 				CascadeType.DETACH: realiza la separacion de los objetos en cascada
	 * 				CascadeType.MERGE: realiza el merge en cascada
	 * 				CascadeType.PERSIST: realiza los insert de los objetos en cascada. o sea si guardo A se guarda B
	 * 				CascadeType.REFRESH: realiza la actualizacion en cascada
	 * 				CascadeType.REMOVE: realiza el delete en cascada
	 * 		optional: <code>true false</code> si el atributo es opcional o no
	 * 		targetEntity: describe la entidad de destino, se utiliza en la relacion bidireccional
	 */
	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

}
