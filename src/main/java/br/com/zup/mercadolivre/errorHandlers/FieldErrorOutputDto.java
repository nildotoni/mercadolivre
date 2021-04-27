package br.com.zup.mercadolivre.errorHandlers;

public class FieldErrorOutputDto {
	
	private String field;
	private String message;
	
	FieldErrorOutputDto(){}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

	public FieldErrorOutputDto(String field, String message) {
		
		this.field = field;
		this.message = message;
	}
	
	

}
