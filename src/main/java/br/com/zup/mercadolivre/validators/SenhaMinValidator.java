package br.com.zup.mercadolivre.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SenhaMinValidator implements ConstraintValidator<SenhaMin, Object>{
		
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value.toString().length() >= 6) {
			return true;
		}
			
		return false;
	}

}
