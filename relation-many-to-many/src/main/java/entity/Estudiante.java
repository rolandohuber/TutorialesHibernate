package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Estudiante {
	private Long id;
	private String name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Curso.class)
	@JoinTable(name = "estudiante_curso", 
				joinColumns = { @JoinColumn(name = "estudiante_id") }, 
								inverseJoinColumns = { @JoinColumn(name = "curso_id") })
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
