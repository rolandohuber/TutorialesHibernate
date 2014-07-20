package entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
/**
 * @MappedSuperclass : se le indica a hibernate que esta clase SOLO se utilizara para heredar de ella, lo cual significa
 * que esta clase no se puede persistir
 * */
public abstract class Entity {

	private Long id;
	private String name;

	public Entity() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
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

}
