package br.com.zup.mercadolivre.controller;


import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.model.Pergunta;
import br.com.zup.mercadolivre.model.Produto;
 
@Service
public class Email implements EnviaEmail{

	
	@Override
	public void envia(Produto produto, Pergunta pergunta) {
		System.out.println("Email Para: " + produto.getDono().getEmail());
		
		  System.out.println("Assunto: Um comprador fez pergunta no anúncio " +
		  produto.getNome());
		  
		  System.out.println("Mensagem: " + " O comprador "+
		  pergunta.getUsuario().getEmail()+" fez pergunta no anúncio " +
		  produto.getNome() +", A pergunta é " + pergunta.getDescricao());
		 
		
	}

	


}
