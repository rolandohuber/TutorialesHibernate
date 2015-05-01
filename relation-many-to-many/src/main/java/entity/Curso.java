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

	public static final String ESTUDIANTES_ATTRIBUTE = "estudiantes";
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

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Estudiante.class, mappedBy = Estudiante.CURSOS_ATTRIBUTE)
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
	 * 		mappedBy: es para indicarle el nombre de la variable en mi clase B que representa a A, solo se utiliza
	 * 				  en la relacion bidireccional,se pone el la relacion mas debil, en este caso se opto por Curso con lo cual debo indicarle el nombre de 
	 * 					mi lista de cursos en mi clase estudiante.
	 * 		targetEntity: es para indicarle la entidad de destino.
	 * 
	 */
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
