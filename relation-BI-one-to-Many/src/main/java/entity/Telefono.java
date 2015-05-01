package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Telefono {
	private Long id;
	private Integer numero;
	private String operador;
	private Persona persona;
	public final static String PERSONA_ATTRIBUTE = "persona";

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

	@ManyToOne(targetEntity = Persona.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	/**
	 * @ManyToOne: con este tag indicamos que la relacion es muchos a uno
	 * 		fetch: indica si va a traer el objeto asociado ya instanciado(EAGER) o si solo traera el id(LAZY), por defecto es EAGER 
	 * 		cascade: es para indicarle que operaciones realiza en cascada
	 * 				CascadeType.ALL: son todas las operaciones, es la que esta por defecto.
	 * 				CascadeType.DETACH: realiza la separacion de los objetos en cascada
	 * 				CascadeType.MERGE: realiza el merge en cascada
	 * 				CascadeType.PERSIST: realiza los insert de los objetos en cascada. o sea si guardo A se guarda B
	 * 				CascadeType.REFRESH: realiza la actualizacion en cascada
	 * 				CascadeType.REMOVE: realiza el delete en cascada
	 *		targetEntity: describe la entidad de destino, generalmente se utiliza en la relacion bidireccional, y tambien nos ayuda a no generar una tabla intermedia
	 *		optional: true/false si el atyributo es opcional o no
	 *		Esto generara tres tablas, una para telefono una para Persona y otra para la asociacion
	 *
	 */
	/**
	 * pero si quisieramos especificar mas datos para la tabla que genera la relacion, en el caso que usemos la tabla intermedia, lo podemos hacer con este tag:
	 * 
	 * @ JoinTable : con este tag especificaremos como queremos que se genere la tabla de relacion
	 * 		name: indica el nombre de la tabla
	 * 		catalog:
	 * 		inverseJoinColumns:
	 * 		uniqueConstraints: se utiliza para agregar restricciones a la hora de insertar un nuevo registro en una tabla,recibe un array de @UniqueConstraint
	 * 		joinColumns: se utiliza para indicarle el nombre de las columnas que quiero que tenga la tabla,recibe un array de @JoinColumn 
	 */
	/*
	 * @ JoinTable (name = "relation" , joinColumns = {
	 * 
	 * @ JoinColumn (name = "personID")}, inverseJoinColumns = {
	 * 
	 * @ JoinColumn (name = "telefonoID" )})
	 */
	// @JoinColumn(name = "id_de_persona")
	/**
	 *@JoinColumn: con este tag le indicamos que agregue una columna para hacer el join con otra tabla, con esto
	 *				logro que no me genere una tabla intermedia entre la asociacion de dos objetos en la relacion oneToMany.
	 * 		
	 * 		name: es el nombre de la columna donde guardara por ejemplo el id del objeto padre al que esta relacionado
	 * 		columnDefinition: se utiliza para definir con sentencias sql una columna en particular, se debe utilizar el 
	 * 						  dialecto de la base de datos con la que voy a trabajar, pero no es aconsejable utilizarlo ya que 
	 * 						  pierdo portabilidad a otro tipo de base de datos  
	 *  	insertable: true/false si es insertable o no
	 *  	nullable: true/false si puede ser nulo o no
	 *  	referencedColumnName:
	 *  	table: 
	 *  	unique: true/false si debe ser unico o no
	 *  	updatable: true/false si puede ser actualizable o no
	 * 
	 */
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
