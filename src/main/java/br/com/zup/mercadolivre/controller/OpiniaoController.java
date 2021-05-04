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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.dto.OpiniaoForm;
import br.com.zup.mercadolivre.model.Opiniao;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
@RestController
@RequestMapping("/opiniao")
//4
public class OpiniaoController {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UsuarioRepository ur;
		//1
	
	@PostMapping
	@Transactional
	//1
	public ResponseEntity<?> cadastraOpiniao(@Valid @RequestBody OpiniaoForm form, UriComponentsBuilder uriBuilder,@AuthenticationPrincipal Usuario usuario) {
		 
		Produto produto = em.find(Produto.class,14L);
		//1
		Opiniao opiniao = form.converter(em,usuario,produto);
		//1
		em.persist(opiniao);
		URI uri = uriBuilder.path("/opiniao/{id}").buildAndExpand(opiniao.getId()).toUri();
		return ResponseEntity.created(uri).body(opiniao.toString());
		
	}

}
