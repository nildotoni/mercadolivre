package br.com.zup.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.validators.SenhaMin;
import br.com.zup.mercadolivre.validators.Unico;

public class usuarioForm {
	
	@Email(message="Por Favor, digite um email válido")
	@NotNull (message="Por favor digite o email de login")
	@NotEmpty(message="Por favor digite o email de login")
	@Unico(atributo = "email", classe= Usuario.class, message="E-mail já existe")
	private String email;
	
	@NotNull (message="Por favor digite a senha")
	@NotEmpty(message="Por favor digite a senha	")
	@SenhaMin(message="Por favor informe uma senha maior que 6 caracteres")
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
