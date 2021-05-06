package br.com.zup.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.jsonwebtoken.lang.Assert;

@Entity
public class Opiniao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Min(1) @Max(5)
	@NotNull
	private int nota;
	@NotBlank @NotNull
	private String titulo;
	@NotBlank @NotNull
	@Length(max=500)
	private String descricao;
	@ManyToOne
	@Valid 
	private Usuario usuario;
	@ManyToOne
	@Valid 
	private Produto produto;
	public Opiniao(@Min(1) @Max(5) @NotNull int nota, @NotBlank @NotNull String titulo,
			@NotBlank @NotNull @Length(max = 500) String descricao, @Valid Usuario usuario,
			@Valid Produto produto) {
		Assert.notNull(nota,"Nota não pode ser nula");
		Assert.notNull(usuario,"O Usuario não pode ser nula");
		Assert.notNull(produto,"O produto não pode ser nula");
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}
	
	@Override
	public String toString() {
		return "Opiniao [id=" + id + ", nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", usuario="
				+ usuario + ", produto=" + produto + "]";
	}

	@Deprecated
	public Opiniao() {	}

	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUsuario() {
		return usuario.getUsername();
	}

	public String getProduto() {
		return produto.getNome();
	}

	public Long getId() {
		return id;
	}
	
	}
