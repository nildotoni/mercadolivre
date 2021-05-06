package br.com.zup.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @Valid
	private String descricao;
	
	@ManyToOne @NotNull @Valid
	private Produto produto;
	
	@ManyToOne @NotNull @Valid
	private Usuario usuario;
	
	private LocalDateTime data = LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Pergunta(@NotNull @Valid String descricao, @NotNull @Valid Produto produto,
			@NotNull @Valid Usuario usuario) {
		
		this.descricao = descricao;
		this.produto = produto;
		this.usuario = usuario;
	}
	
	@Deprecated
	public Pergunta() {	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id 
				+ ", descricao=" + descricao 
				+ ", produto=" + produto.getNome() 
				+ ", usuario=" + usuario.getEmail()
				+ ", dono=" + produto.getDono().getEmail()
				+ "]";
	}
	
	
}
