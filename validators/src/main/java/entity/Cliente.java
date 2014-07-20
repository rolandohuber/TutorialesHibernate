package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import anotations.ValidaUsuario;

@Entity
public class Cliente {

	private Long id;
	private String nombre;
	private String apellido;
	private Date fechaAlta;
	private Double saldo;
	private boolean esClienteVip;
	private String email;
	private String cuit;
	private Direccion direccion;
	private String nombreUsuario;

	@Valid
	/**
	 *	@Valid: este tag se utiliza para indicarle que debe validar el objeto asociado, en el caso que tenga validaciones.
	 */
	@NotNull
	/**
	 *	 @NotNull: con este tag le indicamos que el atributo no puede ser nulo
	 *		groups:
	 *		message: en este atributo le indicamos el mensaje que queremos que devuelva
	 *		payload:
	 */
	@ManyToOne
	@JoinColumn(name = "idDireccion")
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	/**
	 *		@NotEmpty:con este tag le indico que el String que recime no puede ser vacio ( que el valor sea "" )
	 *		groups:
	 *		message: en este atributo le indicamos el mensaje que queremos que devuelva
	 *		payload: 
	 */
	@Length(min = 3, max = 40)
	/**
	 * 	@Length:
	 * 		min: le indicamos el tamaño minimo del atributo
	 * 		max: le indicamos el tamaño maximo del atributo
	 * 		groups:
	 *		message: en este atributo le indicamos el mensaje que queremos que devuelva
	 *		payload:
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@NotEmpty
	@Length(min = 3, max = 40)
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@NotNull
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@AssertTrue
	/**
	 * 	@AssertTrue: con este tag le decimos que no aceptara otro valor que no sea true	
	 * 		groups:
	 *		message: en este atributo le indicamos el mensaje que queremos que devuelva
	 *		payload:
	 */
	public boolean isEsClienteVip() {
		return esClienteVip;
	}

	public void setEsClienteVip(boolean esClienteVip) {
		this.esClienteVip = esClienteVip;
	}

	@NotEmpty
	@Email
	/**
	 *		@Email: con este tag le indicamos que recibira un String con formato de Email
	 * 		groups:
	 *		message: en este atributo le indicamos el mensaje que queremos que devuelva
	 *		payload:
	 *		flags:
	 *		regexp: en este atributo le configuramos la exprecion regular para que valide el Email
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty
	@Pattern(regexp = "^[0-9]{2}-[0-9]{8}-[0-9]$", message = "{cuit.msg}")
	/**
	 * 	  @Pattern: con este tag le agrego una validacion con una exprecion regular
	 * 		groups:
	 *		message: en este atributo le indicamos el mensaje que queremos que devuelva
	 *		payload:
	 *		flags:
	 *		regexp: en este atributo le configuramos la exprecion regular
	 */
	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	@NotNull
	@ValidaUsuario
	/**
	 * @ValidaUsuario: es una anotacion que cree,esto significa que podemos crear nuestras anotaciones
	 * propias para realizar validaciones. Esta anotacion esta en el paquete anotations.
	 * 
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
}
