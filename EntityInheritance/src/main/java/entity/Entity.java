package entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
/**
 * Existen tres maneras de persistir una herencia en hibernate
 * 
 *	Utilizando una sola tabla donde se mapearan todos los objetos y se utilizara un campo mas para diferenciar
 * los objetos(discriminador)
 *  
 *  Utilizando una tabla para el objeto padre y una tabla mas por cada hijo, lo cual hara un join entre las tablas para recuperar el objeto
 *  
 *  y la ultima utilizando una tabla por cada clase
 * 
 */
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
/**
 * @MappedSuperclass : se le indica a hibernate que esta clase SOLO se utilizara para heredar de ella, lo cual significa
 * que esta clase no se puede persistir
 * */
public abstract class Entity {

	private Long id;
	private String nombre;

	public Entity() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
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

}
