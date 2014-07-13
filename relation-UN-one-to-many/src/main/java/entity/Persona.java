package entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

	@OneToMany(fetch = FetchType.LAZY)
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
	 * 		mappedBy: es para indicarle el nombre de la variable en mi clase A que representa a B, solo se utiliza en la relacion bidireccional. 
	 *		orphanRemoval: true/false Es para indicarle que si se elimina el objeto asociado(B) o no, por defecto es false. Lo que quiere decir es 
	 *					   que si yo elimino A tengo no elimina B.
	 *		targetEntity: describe la entidad de destino
	 *
	 *		Esto generara tres tablas, una para telefono una para Persona y otra para la asociacion entre telefono-persona
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
	/*@ JoinTable (name = "relation" , joinColumns = {
	        @ JoinColumn (name = "personID")}, inverseJoinColumns = {
	        @ JoinColumn (name = "telefonoID" )})*/
	/**
	 * para solucionar eso y que no genere una tabla adicional se utiliza el tag @JoinColumn
	 * */
	//@JoinColumn(name="id_persona")
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
	 */
	public List<Telefono> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}
}
