package br.com.zup.mercadolivre.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.dto.ProdutoForm;
import br.com.zup.mercadolivre.model.Produto;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private CaracteristicaRepository cr;
	
	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastroProduto(@Valid @RequestBody ProdutoForm form, UriComponentsBuilder uriBuilder){
		Produto produto = form.converter(cr,em);
		if(produto==null) {
			return ResponseEntity.badRequest().body("Algum dado inválido no Produto");
		}
		em.persist(produto);
		URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(produto);
	}
	
}
