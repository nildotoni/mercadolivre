package br.com.zup.mercadolivre.controller;

import org.springframework.stereotype.Component;

import br.com.zup.mercadolivre.model.Pergunta;
import br.com.zup.mercadolivre.model.Produto;

@Component
public interface EnviaEmailCompra {
	
	public void envia(String para, String assunto, String corpo) ;

}
