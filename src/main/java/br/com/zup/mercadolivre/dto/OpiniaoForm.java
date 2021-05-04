package br.com.zup.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.model.Opiniao;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

public class OpiniaoForm {
	@Min(1) @Max(5)
	@NotNull @Valid
	private int nota;
	@NotBlank @NotNull
	private String titulo;
	@NotBlank @NotNull
	@Length(max=500)
	private String descricao;
		

	public Opiniao converter(EntityManager em, Usuario usuario, Produto produto) {
		
		return new Opiniao(nota, titulo,descricao,usuario,produto);
	}

	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}


	

	
	
	
}
