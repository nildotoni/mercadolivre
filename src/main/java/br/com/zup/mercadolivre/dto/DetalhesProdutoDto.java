package br.com.zup.mercadolivre.dto;

import java.util.Set;

import javax.persistence.EntityManager;

import br.com.zup.mercadolivre.model.Opiniao;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.repository.OpiniaoRepository;

public class DetalhesProdutoDto {

	private String nome;
	private Double preço;
	private String descricao;
	private Set<String> linksImagem;
	// 1
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<DetalheProdutoCaracteristica> outrasCaracteristicas;
	// 1
	private Opinioes opinioes;
	
	private Double notaMedia;
	private Integer qntNotas;


	public DetalhesProdutoDto(EntityManager em, Long id, OpiniaoRepository op) {
		// 1
		Produto produto = em.find(Produto.class, id);
		// 1
		Set<Opiniao> listaOpinioes = op.findAllByProduto_id(id);
		this.nome = produto.getNome();
		this.preço = produto.getValor();
		this.descricao = produto.getDescricao();
		// 1
		this.linksImagem = produto.mapeiaImagens(imagem -> imagem.getLink());
		this.caracteristicas = produto.mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);
		this.outrasCaracteristicas = produto.mapeiaOutrasCaracteristicas(DetalheProdutoCaracteristica::new);
		// 1
		

		this.opinioes = new Opinioes(listaOpinioes);
		
		this.notaMedia = opinioes.media();
		this.qntNotas = opinioes.qntNotas();
	}

	public String getNome() {
		return nome;
	}

	public Double getPreço() {
		return preço;
	}

	public String getDescricao() {
		return descricao;
	}

	public Set<String> getLinksImagem() {
		return linksImagem;
	}

	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<DetalheProdutoCaracteristica> getOutrasCaracteristicas() {
		return outrasCaracteristicas;
	}

	
	public Opinioes getOpinioes() {
		return opinioes;
	}

	public Double getNotaMedia() {
		return notaMedia;
	}

	public Integer getQntNotas() {
		return qntNotas;
	}

}
