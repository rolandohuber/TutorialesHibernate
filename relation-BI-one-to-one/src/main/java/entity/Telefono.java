package entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Telefono {
	private Long id;
	private Integer numero;
	private String operador;
	private Persona persona;

	public Telefono() {
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	@OneToOne(targetEntity = Persona.class, fetch = FetchType.LAZY,mappedBy=Persona.TELEFONO_ATTRIBUTE)
	/**
	 * Clase A contiene a Clase B
	 * @OneToOne: con este tag indicamos que la relacion es uno a uno
	 * 		fetch: indica si va a traer el objeto asociado ya instanciado(EAGER) o si solo traera el id(LAZY), por defecto es EAGER 
	 * 		cascade: es para indicarle que operaciones realiza en cascada
	 * 				CascadeType.ALL: son todas las operaciones, es la que esta por defecto.
	 * 				CascadeType.DETACH: realiza la separacion de los objetos en cascada
	 * 				CascadeType.MERGE: realiza el merge en cascada
	 * 				CascadeType.PERSIST: realiza los insert de los objetos en cascada. o sea si guardo A se guarda B
	 * 				CascadeType.REFRESH: realiza la actualizacion en cascada
	 * 				CascadeType.REMOVE: realiza el delete en cascada
	 * 		mappedBy: es para indicarle el nombre de la variable en mi clase A que representa a B, solo se utiliza en la relacion bidireccional. 
	 *		optional: true/false indican si el atributo es opcional/requerido
	 *		orphanRemoval: true/false Es para indicarle que si se elimina el objeto asociado(B) o no, por defecto es false. Lo que quiere decir es 
	 *					   que si yo elimino A tengo no elimina B.
	 *		targetEntity: describe la entidad de destino
	 */
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
