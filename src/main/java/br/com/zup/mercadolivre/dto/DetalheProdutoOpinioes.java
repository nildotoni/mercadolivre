package br.com.zup.mercadolivre.dto;

import br.com.zup.mercadolivre.model.Opiniao;

public class DetalheProdutoOpinioes {

	private int nota;
	private String titulo;
	private String descricao;
	private String usuario;
	private String produto;

	public DetalheProdutoOpinioes(Opiniao opiniao) {
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
		this.usuario = opiniao.getUsuario();
		this.produto = opiniao.getProduto();
	}



}
