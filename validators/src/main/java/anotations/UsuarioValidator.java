package anotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsuarioValidator implements ConstraintValidator<ValidaUsuario, String> {

	public void initialize(ValidaUsuario validaUsuario) {
		// TODO Auto-generated method stub
		System.out.println("Init del validador para validaUsuario");
	}

	public boolean isValid(String valor, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		System.out.println("Validando el usuario: " + valor);

		if (valor == null)
			return true;

		if (valor.equals("pepe"))
			return false;

		return false;
	}

}
