package br.com.zup.mercadolivre.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.CaracteristicaRepository;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
import io.jsonwebtoken.lang.Assert;
import br.com.zup.mercadolivre.model.Caracteristica;
import br.com.zup.mercadolivre.model.Categoria;
//4
public class ProdutoForm {

	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@Positive
	private Double valor;
	@NotNull
	@Positive
	private Integer quantidade;
	@NotNull
	@NotEmpty
	@Column(length = 1000)
	private String descricao;
	@NotNull
	private Long categoria_id;
	@Size(min = 3)
	@NotNull
	private ArrayList<Long> caracteristicas = new ArrayList<>();

	private ArrayList<Long> outrascaracteristicas = new ArrayList<>();

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

	public Long getCategoria_id() {
		return categoria_id;
	}

	public List<Long> getCaracteristicas() {
		return caracteristicas;
	}

	public List<Long> getOutrascaracteristicas() {
		return outrascaracteristicas;
	}

	public List<Caracteristica> listaCaracteristicas( ArrayList<Long> caracteristicas, CaracteristicaRepository cr) {
		//1	
		List<Caracteristica> listaCaracteristicas = new ArrayList<>();
		for (int i = 0; i < caracteristicas.size(); i++) {
			//1
			Optional<Caracteristica> c = cr.findById(caracteristicas.get(i));
			if (c.isPresent()) {
				//1
				listaCaracteristicas.add(c.get());
			}
		}
		return listaCaracteristicas;
	}

	public Produto converter(CaracteristicaRepository cr, EntityManager em, UsuarioRepository ur) {
		//1
		Optional<Usuario> dono = ur.findByEmail("nildo@email.com");
		List<Caracteristica> listaCaracteristicas = listaCaracteristicas(caracteristicas, cr);
		List<Caracteristica> listaOutrasCaracteristicas = listaCaracteristicas(outrascaracteristicas, cr);
		Categoria categoria = em.find(Categoria.class, categoria_id);
		return new Produto(dono.get(),nome,valor,quantidade,descricao,categoria,listaCaracteristicas,listaOutrasCaracteristicas);
	}

}
