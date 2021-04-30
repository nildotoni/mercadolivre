package br.com.zup.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Caracteristica {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;
	@NotNull @NotEmpty
	private String nome;
	private String descricao;
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public Caracteristica(@NotNull @NotEmpty String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	@Deprecated
	public Caracteristica() {	}
	
	
}
