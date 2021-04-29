package br.com.zup.mercadolivre.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class Login {

	@NotNull @NotEmpty
	private String email;
	
	@NotNull @NotEmpty @Length(min=6)
	private String senha;

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		
		return new UsernamePasswordAuthenticationToken(email,senha);
	}
	
}
