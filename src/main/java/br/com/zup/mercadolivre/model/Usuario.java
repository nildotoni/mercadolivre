package br.com.zup.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull 
	@NotEmpty
	@Column(unique = true)
	private String email;
	
	@NotNull 
	@NotEmpty
	private String senha;
	
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	public Usuario(String email,String senha) {
		this.email = email;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	};
		
	
}
