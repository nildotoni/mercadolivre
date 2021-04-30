package br.com.zup.mercadolivre.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.jsonwebtoken.lang.Assert;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	@NotNull 
	private Double valor;
	@NotNull 
	private Integer quantidade;
	@NotBlank @Length(max=1000) 
	private String descricao;
	
	@ManyToOne
	private Categoria categoria;
	
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@ManyToMany
	private List<Caracteristica> caracteristicas;
	
	@ManyToMany
	private List<Caracteristica> outrasCaracteristicas;
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public Double getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	public List<Caracteristica> getoutrasCaracteristicas() {
		return outrasCaracteristicas;
	}
	public Produto(String nome, Double valor,
			Integer quantidade, String descricao, Categoria categoria, List<Caracteristica> caracteristicas,List<Caracteristica> outrascaracteristicas) {
		Assert.notNull(nome, "Nome não pode ser nulo");
		Assert.notNull(valor, "o Valor não pode ser nulo");
		Assert.isTrue(valor > 0, "Quantidade não pode ser menor que zero");
		Assert.isTrue(quantidade > 0, "Quantidade não pode ser menor que zero");
		Assert.notNull(categoria,"Categoria não pode ser nulo");
		Assert.notNull(caracteristicas,"Caracteristicas não pode ser nulo");
		Assert.isTrue(caracteristicas.size() >= 3,"Não pode ter menos que 3 caracteristicas");
		Assert.isTrue(descricao.length() <= 1000, "Descrição não pode ser maior que 1000");
		Assert.notNull(outrascaracteristicas, "Outras Categorias não pode ser nula");
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas = caracteristicas;
		this.outrasCaracteristicas = outrascaracteristicas;
	}
	
	@Deprecated
	public Produto() {	}
	
	
}
