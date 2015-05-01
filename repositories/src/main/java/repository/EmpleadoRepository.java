package repository;

import entity.Empleado;

public class EmpleadoRepository extends EntityRepository<Empleado> {

	public EmpleadoRepository() {
		super(Empleado.class);
	}

}
