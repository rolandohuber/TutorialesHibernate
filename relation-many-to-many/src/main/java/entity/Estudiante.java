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
	public static final String CURSOS_ATTRIBUTE = "cursos";

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
	/**
	 * @ManyToMany: con este tag le indicamos a hibernate que la relacion va a ser muchos a muchos, por defecto genera dos tablas de relaciones,una
	 * 				 viendola desde estudiante y la otra viendola desde curso
	 * 		fetch: indica si va a traer el objeto asociado ya instanciado(EAGER) o si solo traera el id(LAZY), por defecto es EAGER 
	 * 		cascade: es para indicarle que operaciones realiza en cascada
	 * 				CascadeType.ALL: son todas las operaciones, es la que esta por defecto.
	 * 				CascadeType.DETACH: realiza la separacion de los objetos en cascada
	 * 				CascadeType.MERGE: realiza el merge en cascada
	 * 				CascadeType.PERSIST: realiza los insert de los objetos en cascada. o sea si guardo A se guarda B
	 * 				CascadeType.REFRESH: realiza la actualizacion en cascada
	 * 				CascadeType.REMOVE: realiza el delete en cascada
	 * 		mappedBy: es para indicarle el nombre de la variable en mi clase B que representa a A, solo se utiliza en la relacion bidireccional, y tambien nos ayuda a no generar dos tablas intermedias, se pone el la relacion mas debil, en este caso se opto por Curso
	 * 		targetEntity: es para indicarle la entidad de destino.
	 * 
	 */
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
