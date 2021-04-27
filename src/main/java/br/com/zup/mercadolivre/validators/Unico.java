	package br.com.zup.mercadolivre.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME; //precisei por na mão
import static java.lang.annotation.ElementType.FIELD; //precisei por na mão

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {UnicoValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface Unico {
//1
	String message() default "{br.com.zup.mercadolivre.validators.unico}";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default { };
	
	String atributo();
	
	Class<?> classe();
}
