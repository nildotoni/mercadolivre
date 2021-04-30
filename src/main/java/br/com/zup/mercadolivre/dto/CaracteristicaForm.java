package br.com.zup.mercadolivre.dto;


import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.model.Caracteristica;
import io.jsonwebtoken.lang.Assert;

public class CaracteristicaForm {

	@NotNull @NotEmpty
	private String nome;
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public CaracteristicaForm(String nome, String descricao) {
		Assert.notNull(nome,"Nome da caracteristica não pode ser nulo");
		this.nome = nome;
		this.descricao = descricao;
	}
	
	@Deprecated
	public CaracteristicaForm() {	}
	
	public Caracteristica converter(EntityManager entityManager) {
		
		return new Caracteristica(nome,descricao);
	}
}
