package br.com.zup.mercadolivre.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.dto.DetalheProdutoCaracteristica;
import br.com.zup.mercadolivre.dto.DetalheProdutoOpinioes;
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
	@NotBlank
	@Length(max = 1000)
	private String descricao;

	@ManyToOne
	private Categoria categoria;

	private LocalDateTime dataCadastro = LocalDateTime.now();

	@ManyToMany
	private List<Caracteristica> caracteristicas;

	@ManyToMany
	private List<Caracteristica> outrasCaracteristicas;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();

	@NotNull
	@Valid
	@ManyToOne
	private Usuario dono;


	public Produto(Usuario dono, String nome, Double valor, Integer quantidade, String descricao, Categoria categoria,
			List<Caracteristica> caracteristicas, List<Caracteristica> outrascaracteristicas) {
		Assert.notNull(nome, "Nome não pode ser nulo");
		Assert.notNull(valor, "o Valor não pode ser nulo");
		Assert.isTrue(valor > 0, "Quantidade não pode ser menor que zero");
		Assert.isTrue(quantidade > 0, "Quantidade não pode ser menor que zero");
		Assert.notNull(categoria, "Categoria não pode ser nulo");
		Assert.notNull(caracteristicas, "Caracteristicas não pode ser nulo");
		Assert.isTrue(caracteristicas.size() >= 3, "Não pode ter menos que 3 caracteristicas");
		Assert.isTrue(descricao.length() <= 1000, "Descrição não pode ser maior que 1000");
		Assert.notNull(outrascaracteristicas, "Outras Categorias não pode ser nula");
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas = caracteristicas;
		this.outrasCaracteristicas = outrascaracteristicas;
		this.dono = dono;
	}

	@Deprecated
	public Produto() {
	}

	public void associa(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade
				+ ", descricao=" + descricao + ", categoria=" + categoria + ", dataCadastro=" + dataCadastro
				+ ", caracteristicas=" + caracteristicas + ", outrasCaracteristicas=" + outrasCaracteristicas
				+ ", imagens=" + imagens + ", dono=" + dono + "]";
	}

	

	
	public boolean pertenceAoUsuario(Usuario possivelDono) {
		return this.dono.equals(possivelDono);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dono == null) ? 0 : dono.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((outrasCaracteristicas == null) ? 0 : outrasCaracteristicas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (dono == null) {
			if (other.dono != null)
				return false;
		} else if (!dono.equals(other.dono))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (outrasCaracteristicas == null) {
			if (other.outrasCaracteristicas != null)
				return false;
		} else if (!outrasCaracteristicas.equals(other.outrasCaracteristicas))
			return false;
		return true;
	}

	public <T> Set<T> mapeiaImagens(Function<ImagemProduto, T> funcaoMapeadora) {

		return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T> Set<T> mapeiaCaracteristicas(Function<Caracteristica, T> funcaoMapeadora) {

		return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T> Set<T> mapeiaOutrasCaracteristicas(Function<Caracteristica, T> funcaoMapeadora) {

		return this.outrasCaracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

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

	public List<Caracteristica> getOutrasCaracteristicas() {
		return outrasCaracteristicas;
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public Usuario getDono() {
		return dono;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}




	
	
}
