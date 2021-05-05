package br.com.zup.mercadolivre.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.dto.PerguntaForm;
import br.com.zup.mercadolivre.model.Pergunta;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
//4
@RestController
public class PerguntaController {
	
	@PersistenceContext
	private EntityManager em;
	//1 
	@Autowired
	private Email email;
	
	@PostMapping(value="/produto/{produtoid}/pergunta")
	@Transactional
	public ResponseEntity<?> cadastraPergunta(@PathVariable("produtoid") Long id,@Valid @RequestBody PerguntaForm form,
			@AuthenticationPrincipal Usuario usuario, UriComponentsBuilder uriBuilder) {
		//1 	
		Produto produto = em.find(Produto.class, id);
		//1 
		Pergunta pergunta = form.toModel(produto,usuario);
		em.persist(pergunta);
		
		//1 	
		email.envia(produto,pergunta);
		URI uri = uriBuilder.path("/pergunta/{id}").buildAndExpand(pergunta.getId()).toUri();
		return ResponseEntity.created(uri).body(pergunta.toString());
		
		
	}
	
}
