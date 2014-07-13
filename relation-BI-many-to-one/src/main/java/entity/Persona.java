package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Persona implements Serializable {
	private static final long serialVersionUID = -3481041518539559833L;

	private Long id;
	private String lastName;
	private String dni;
	private String name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	/**
	 * tambien puedo pedirle que me genere una tabla intermedia...
	 * 
	 */
	/**
	 * pero si quisieramos generar una tabla para la relacion lo podemos hacer con este tag:
	 * 
	 * @ JoinTable : con este tag especificaremos como queremos que se genere la tabla de relacion
	 * 		name: indica el nombre de la tabla
	 * 		catalog:
	 * 		inverseJoinColumns:
	 * 		uniqueConstraints: se utiliza para agregar restricciones a la hora de insertar un nuevo registro en una tabla,recibe un array de @UniqueConstraint
	 * 		joinColumns: se utiliza para indicarle el nombre de las columnas que quiero que tenga la tabla,recibe un array de @JoinColumn 
	 */
	/*@ JoinTable (name = "relation" , joinColumns = {
	        @ JoinColumn (name = "personID")}, inverseJoinColumns = {
	        @ JoinColumn (name = "telefonoID" )})*/
	//@JoinColumn(name = "id_de_casa")
	/**
	 *@JoinColumn: con este tag le indicamos que agregue una columna para hacer el join con otra tabla, con esto
	 *				logro que no me genere una tabla intermedia entre la asociacion de dos objetos en la relacion ManyToOne, aunque por defecto en la relacion manyToOne me lo genere solo
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
	public Casa getCasa() {
		return casa;
	}

	public void setCasa(Casa casa) {
		this.casa = casa;
	}

}
