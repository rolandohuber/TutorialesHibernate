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

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
public class Estudiante {
	public static final String CURSOS_ATTRIBUTE = "cursos";
	public static final String NAME_ATTRIBUTE = "name";
	public static final String LASTNAME_ATTRIBUTE = "lastname";

	private Long id;
	private String name;
	private String lastname;
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

	@Field(index = Index.YES, store = Store.NO)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Field(index = Index.YES, store = Store.NO)
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Curso.class)
	/*
	 *@ContainedIn:
	 *@IndexedEmbedded
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
