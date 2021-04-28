package br.com.zup.mercadolivre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
public class Categoria {
	
	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true) @NotNull @NotEmpty
	private String nome;
	
	@ManyToOne
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria(@NotNull @NotEmpty String nome) {
		Assert.notNull(nome, "O nome não pode ser nulo");
		this.nome = nome;
		}

	@Deprecated
	public Categoria() {}

		
	}
	
	

