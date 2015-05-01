package entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity(name = "Persona")
/**
 * @Entity con este tag le indico a hibernate que es una clase que se puede guardar
 * 		name:(opcional) es el nombre que le quiero dar a la entidad, por defecto es el nombre de la clase.
 */
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre", "dni" }) })
/**
 * 
 * @Table: (opcional) Con este tag le puedo indicar a hibernate configuraciones de la tabla
 * 		catalog:
 * 		name: es el nombre que le quiero dar a la tabla
 * 		schema:
 * 		uniqueConstraints: es para agregarle restricciones a cada registro de la tabla, recibe un array de @UniqueConstraint, en el ejemplo le indico que no puede haber dos
 * 							registros con el nombre y dni iguales.
 * 			@UniqueConstraint: es para indicarle sobre que columnas quiero agregar a la resticcion
 * 				columnNames: es un array de String con el nombre exacto de las columnas en la base
 *
 */
public class Persona implements Serializable {

	private static final long serialVersionUID = -3481041518539559833L;

	private Long id;
	private String name;
	private String dni;
	private Integer telefone;
	private Long version;
	private String transientValue;
	private java.util.Date fecha_nacim;
	private int edad;
	private Example example;

	public Persona() {
	}

	@Id
	/**
	 *	@Id: con este tag le indico que este atributo va a ser mi id en la base de datos 
	 */
	@GeneratedValue
	/**
	 * @GeneratedValue: con este tag nos generara los ids de los objetos que persistamos, si no lo ponemos tendremos que setearle
	 * 					 un id manualmente.
	 * 		generator: aqui es donde le indicamos como queremos que genere los ids,recibe un GenerationType, por defecto es AUTO
	 * 			GenerationType.AUTO: genera los ids con la estrategia por defecto de la base
	 * 			GenerationType.IDENTITY: mapea la clave a una columna contadora
	 * 			GenerationType.SEQUENCE: genera los ids de manera secuencial
	 * 			GenerationType.TABLE:genera los ids con una tabla
	 * 
	 * 		strategy: ,recibe un GenerationType
	 * 			GenerationType.AUTO: genera los ids con la estrategia por defecto de la base
	 * 			GenerationType.IDENTITY: mapea la clave a una columna contadora
	 * 			GenerationType.SEQUENCE: genera los ids de manera secuencial
	 * 			GenerationType.TABLE:genera los ids con una tabla
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nombre")
	/**
	 * 	@Column: con este tag le especificamos configuraciones al momento de generar la columna.
	 * 
	 * 		name (opcional): conesto le indicamos el nombre de la columna (por defecto es el nombre del atributo)
	 *		unique (opcional): setea si el atributo es unico(true) para la columna o no (por defecto no(false))
	 *		nullable (opcional): setea si puede ser nulo(true) o no(false)(por defecto true).
	 *		insertable (opcional): indica si en el insert del registro se debe guardar el dato(true) o no(false)(por defecto es true)
	 *		updatable (opcional): indica si en el update del registro se debe actualizar el dato(true) o no(false)(por defecto es true)
	 *		columnDefinition (opcional): aqui le pasamos la sentencia, en el lenguaje especifico de la base de datos,
	 *									 indicando como tiene que generar esta columna, no es recomendable ya que quedas ligado a un solo tipo de base de datos,
	 *									y en el caso de una migracion hay que actualizar la sentencia(por defecto la genera hibernate usando el lenguaje del motor de la base de datos configurada)
	 *		table (opcional): con esto le indicamos si queremos que valla a otra tabla en particular (por defecto va a la tabla de nuestra entidad)
	 *		length (opcional): indica el tamaño max del dato que vamos a guardar (por defecto es 255)
	 *		precision (opcional): es la precicion decimal de la columna (por defecto es 0)
	 *		scale (opcional): indica la escala decimal de la columna (por defecto es 0)
	 */
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
	/**
	 * @Version: con este tag le indicamos que va a ser la version del objeto, se actualiza automaticamente en la base de datos
	 * 			y no es recomendable setearle el valor manualmente. Es mejor dejarle ese trabajo a hibernate
	 */
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Transient
	/**
	 *	@Transient: con este tag le indicamos a hibernate que no queremos que se mapee a la base esta variable 
	 */
	public String getTransientValue() {
		return transientValue;
	}

	public void setTransientValue(String transientValue) {
		this.transientValue = transientValue;
	}

	@Basic(fetch = FetchType.LAZY, optional = true)
	/**
	 * @Basic: con este tag mapeamos atributos a los cuales queremos especificar el modo de recuperacion y si es opcional o no
	 * 		fetch: recibe un FetchType, con esto se le indica si queremos que traiga el objeto con este valor ya cargado(EAGER) o 
	 * 				si queremos que recien se traiga cuando se lo pide(LAZY) 
	 * 		optional:true/false si el atributo es opcional o no
	 */
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Temporal(TemporalType.DATE)
	/**
	 * 	@Temporal: con este tag le indicamos que va a ser un dato del tipo temporal, recibe un TemporalType
	 * 			TemporalType.TIMESTAMP: guarda dia mes año hora minutos y segundos
	 * 			TemporalType.TIME: guarda hora minutos y segundos
	 * 			TemporalType.DATE: guarda sia mes año
	 */
	public java.util.Date getFecha_nacim() {
		return fecha_nacim;
	}

	public void setFecha_nacim(java.util.Date fecha_nacim) {
		this.fecha_nacim = fecha_nacim;
	}

	@Enumerated(EnumType.STRING)
	/**
	 *	@Enumerated: con este tag le indicamos que este atributo va a ser un enumerado por defecto es EnumType.ORDINAL
	 *				EnumType.STRING: con esto le indicamos que el tipo de dato que va a guardar es un String
	 *				EnumType.ORDINAL: con este tag le indicamos que el tipo de dato que va a guardar es 
	 */
	public Example getExample() {
		return example;
	}

	public void setExample(Example example) {
		this.example = example;
	}

	public enum Example {
		ONE(1, "ONE"), TWO(2, "TWO");
		private int value;
		private String name;

		private Example(int value, String name) {
			this.value = value;
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public String getName() {
			return this.name;
		}
	}

}
