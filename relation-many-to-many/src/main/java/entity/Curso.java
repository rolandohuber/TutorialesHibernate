package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Curso {

	private Long id;
	private String nombre;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Estudiante.class,mappedBy="cursos")
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
