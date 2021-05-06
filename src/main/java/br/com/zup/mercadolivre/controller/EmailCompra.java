package br.com.zup.mercadolivre.controller;


import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;


 
@Service
public class EmailCompra implements EnviaEmailCompra{
	private String para;
	private String assunto;
	private String corpo;

	public void enviaEmail(Usuario comprador, Produto produto) {
		this.para = "Email para: " + produto.getDono().getEmail();
		this.assunto = "Venda realizada do produto " + produto.getNome();
		this.corpo = "O produto " + produto.getNome() + " Foi vendido para o comprador " + comprador.getUsername();
		envia(para, assunto, corpo);
	}
	
	
	@Override
	public void envia(String para, String assunto, String corpo) {
		System.out.println(para);
		System.out.println(assunto);
		System.out.println(corpo);
		
	}

	


}
