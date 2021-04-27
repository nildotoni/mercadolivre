package br.com.zup.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.validators.SenhaMin;
import br.com.zup.mercadolivre.validators.Unico;

public class usuarioForm {
	
	@Email
	@NotNull
	@NotEmpty
	@Unico(atributo = "email", classe= Usuario.class) 
	private String email;
	
	@NotNull
	@NotEmpty
	@SenhaMin
	private String senha;

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Usuario converter() {
		return new Usuario(email,senha);
	}
	
	
	
}
