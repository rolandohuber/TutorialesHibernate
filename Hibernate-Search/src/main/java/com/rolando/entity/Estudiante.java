package com.rolando.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

@Entity
/*
 * @Indexed con esta anotation le digo que va a ser un objeto indexable
 */
@Indexed
public class Estudiante {
	public static final String CURSOS_ATTRIBUTE = "cursos";
	public static final String NOMBRE_ATTRIBUTE = "nombre";
	public static final String APELLIDO_ATTRIBUTE = "apellido";

	private Long id;
	private String nombre;
	private String apellido;
	private Set<Curso> cursos;

	public Estudiante() {
		this.cursos = new HashSet<Curso>();
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
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Curso.class)
	/*
	 *@ContainedIn : se utiliza esta anotacion para indicar que es una lista para que lo reconozca el indexador
	 *@IndexedEmbedded: con esto le decimos a hibernate-search que tiene una entidad asociada
	 * */
	@ContainedIn
	@IndexedEmbedded
	@JoinTable(name = "estudiante_curso", joinColumns = { @JoinColumn(name = "estudiante_id") }, inverseJoinColumns = { @JoinColumn(name = "curso_id") })
	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}

	public void addCurso(Curso curso) {
		this.cursos.add(curso);
	}

}
