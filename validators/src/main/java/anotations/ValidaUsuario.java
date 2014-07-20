package anotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UsuarioValidator.class)
@Documented
public @interface ValidaUsuario {

	/**
	 * {validausuario.msg} es una key para el mensaje de error por defecto de la validacion
	 * se encuentra en el archivo ValidationMessages.properties.
	 * 
	 * las anotaciones de validaciones poseen mensajes por defecto los cuales se encuentran internacionalizados 
	 * en el paquete org.hibernate.validator, nosotros podemos sobreescribir estos mensajes o crear los propios
	 * en un archivo llamado ValidationMessages.properties.
	 * 
	 */
    String message() default "{validausuario.msg}";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}