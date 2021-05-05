package br.com.zup.mercadolivre.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.model.Pergunta;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

public class PerguntaForm {
	
	@NotNull @Valid
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public Pergunta toModel(Produto produto, Usuario usuario) {
	
		return new Pergunta(descricao,produto,usuario);
	}

	
	
}
