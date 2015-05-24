package com.rolando.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
/*
 * @Indexed con esta anotacion le digo que va a ser un objeto indexable
 */
@Indexed
public class Curso {

	public static final String ESTUDIANTES_ATTRIBUTE = "estudiantes";
	public static final String NAME_ATTRIBUTE = "nombre";
	public static final String DESCRIPCION_ATTRIBUTE = "descripcion";
	public static final String PRIORIDAD_ATTRIBUTE = "prioridad";
	public static final String FECHA_CREACION_ATTRIBUTE = "fechaCreacion";
	
	private Long id;
	private String nombre;
	private String descripcion;
	private Integer prioridad;
	private Date fechaCreacion;
	private Set<Estudiante> estudiantes;

	public Curso() {
		this.estudiantes = new HashSet<Estudiante>();
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * @Field: con esto se le indica que el campo sera indexado y se podra buscar en el
	 * 		index: YES con esto se asegura que el texto sera indexado
	 * 		store: NO le decimos que no va a guardar los valores en el indice,
	 * 		analize: YES con esto excluye palabras comunes como a,la,etc
	 */
	@Field(index = Index.YES, store = Store.NO,analyze=Analyze.YES)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Field(index = Index.YES, store = Store.NO,analyze=Analyze.YES)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Field(index = Index.YES, store = Store.NO,analyze=Analyze.YES)
	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	@Field(index = Index.YES, store = Store.NO,analyze=Analyze.YES)
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date date) {
		this.fechaCreacion = date;
	}

	/*
	 * @ContainedIn : se utiliza esta anotacion para indicar que es una lista para que lo reconozca el indexador
	 */
	@ContainedIn
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Estudiante.class, mappedBy = Estudiante.CURSOS_ATTRIBUTE)
	public Set<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(Set<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public void addEstudiante(Estudiante estudiante) {
		this.estudiantes.add(estudiante);
	}
}
