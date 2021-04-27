package br.com.zup.mercadolivre.errorHandlers;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDto {

	private List<String> globalErrorMessages = new ArrayList<>();
	private List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();
	
	public List<String> getGlobalErrorMessages() {
		return globalErrorMessages;
	}
	public List<FieldErrorOutputDto> getFieldErrors() {
		return fieldErrors;
	}
	public void addFieldError(String field,String message) {
		
		FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field,message);
		fieldErrors.add(fieldError);
	}
	
	ValidationErrorsOutputDto(){}
	
	public void addError(String message) {
		
		globalErrorMessages.add(message);
	}
	
}
