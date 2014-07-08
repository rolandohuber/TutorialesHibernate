package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity(name = "Persona")
/**
 *@Entity : con esta anotacion le indicamos a hibernate que es una clase que se puede persistir
 *		name: , por defecto es el nombre de la Clase
 *	
 *
 * NOTA es requisito crear el contructor vacio
 * */
@Table(name = "personas",uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
/**
 * @Table : con este tag le indicaremos a hibernate configuraciones de la tabla en la que se van a guardar
 * 		name : con este atributo le indico como quiero que se llame la tabla en la que se va a guardar este objeto, por defecto es el nombre de la Clase
 * 		catalog :
 *		schema :
 *		uniqueConstraints: dentro de este atributo se coloca una restriccion a la tabla que me indica que por cada registro que inserto en la base de
 *							datos no puede haber dos registros que tengan los mismos datos en las columnas especificadas. Si se intenta insertar dos registros iguales lanza una Excepcion de Duplicate entry
 *			@UniqueConstraint: con este tag le indicamos sobre que columnas tiene que realizar la restriccion
 *					columnNames:destro de este atributo le colocamos el nombre de las columnas que deseamos agregar a la restriccion.
 *					Ejemplo de restriccion: uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) con esto le digo que no pueden existir dos registros que 
 *					tengan los mismos datos dentro de la columna name o sea que <b>no pueden haber dos o mas registros</b> que se llamen Carlos
 */
public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3481041518539559833L;

	private Long id;
	private String name;
	private Long version;
	private String notMapped;
	private int edad;
	private String presentacion;
	private Date birthDay;
	
	public Persona() {
	}

	@Id
	/**
	 *@Id : con este tag le indicamos que este campo va a ser el id del objeto 
	 * */
	@GeneratedValue(strategy=GenerationType.AUTO)
	/**
	 * @GeneratedValue :con este tag configuramos el modo en que se generara el id de este objeto en la base de datos
	 * 		generator:
	 * 		strategy: es la forma en la que hibernate ira generando el id en la base de datos
	 * 				AUTO: toma automaticamente la estrategia de la base con la que trabajamos, es la opcion por defecto
	 * 				IDENTITY: generara los ids incrementalmente
	 * 				TABLE: una tabla es la encargada de proveer los ids para las entidades, se especifica con el tag  @TableGenerator
	 * 				SEQUENCE: genera el id de acuerdo a una secuencia especificada en el tag @SequenceGenerator
	 */
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

	@Version
	/**
	 * @Version : con este tag le indicamos que usaremos este campo para que nos genere la version del objeto, lo mas recomendable
	 *  es usar un tipo de dato numerico
	 * 
	 * solo se debe utilizar el getter ya que la aplicacion de ninguna manera debe modificar el valor de la version, de esto se encarga hibernate
	 */
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version){
		this.version=version;
	}
	@Transient
	/**
	 * 
	 * @Transient : con este tag le indicamos a hibernate que no queremos que mapee este atributo, o sea que no me 
	 * 				guarde ni genere la columna para este atributo en la base de datos
	 */
	public String getNotMapped() {
		return notMapped;
	}

	public void setNotMapped(String notMapped) {
		this.notMapped = notMapped;
	}

	@Basic(optional=true,fetch=FetchType.LAZY)
	/**
	 * @Basic: con este tag le indico que es un tipo basico
	 * 		optional : le indico si es opcional o no
	 * 		fetch: indica el tipo de carga de este atributo 
	 */
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Column(name="presentacion")
	/**
	 * @Column: con este tag puedo configurar parametros de la columna en la que quiero que se guarde este atributo
	 * 		columnDefinition:(opcional)  le indico el tipo de dato con el que quiero que lo guarde en la base de datos.
	 * 		insertable:(opcional)  le indico si es insertable o no(true/false), por defecto es true
	 * 		length:(opcional)  le indico el tamaño/longitud de la columna, por defecto es 255
	 * 		name:(opcional) le indico el nombre de la columna para este campo, por defecto es el nombre del atributo
	 * 		nullable:(opcional)  le indico si este campo puede ser nulo o no, por defecto es true
	 * 		precision:(opcional)  AVERIGUAR ,por defecto es 0
	 * 		scale:(opcional)  AVERIGUAR ,por defecto es 0
	 * 		table:(opcional)  le indico una tabla especifica donde quiero que lo guarde
	 * 		unique:(opcional) le indico si tiene que ser unico o no, por defecto false
	 * 		updatable:(opcional) le indico si puedo actualizar este campo o no, por defecto es true
	 */
	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	/**
	 * @Temporal: con este tag le indicamos que es un tipo de dato de tiempo
	 */
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	
}
