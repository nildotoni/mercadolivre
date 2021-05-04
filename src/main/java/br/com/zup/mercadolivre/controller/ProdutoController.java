package br.com.zup.mercadolivre.controller;

import java.net.URI;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.dto.NovasImagensRequest;
import br.com.zup.mercadolivre.dto.ProdutoForm;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.repository.CaracteristicaRepository;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
//8
@RestController
@RequestMapping("/produto")
public class ProdutoController {
	//1
	@Autowired
	private CaracteristicaRepository cr;
	//1
	@Autowired
	private UsuarioRepository ur;
	//1
	@PersistenceContext
	private EntityManager em;
	//1
	@Autowired
	private UploaderFake uploaderFake;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastroProduto(@Valid @RequestBody ProdutoForm form, UriComponentsBuilder uriBuilder){
		//1
		Produto produto = form.converter(cr,em,ur);
		if(produto==null) {
			//1
			return ResponseEntity.badRequest().body("Algum dado inválido no Produto");
		}
		em.persist(produto);
		URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(produto.toString());
	}

	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public ResponseEntity<?> cadastroImagens(@PathVariable("id") long id, @Valid NovasImagensRequest request, UriComponentsBuilder uriBuilder) {
		//1
		Usuario dono = ur.findByEmail("nildo@email.com").get();
		Produto produto = em.find(Produto.class, id);
		
		if(!produto.pertenceAoUsuario(dono)) {
			//1
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Set<String> links = uploaderFake.envia(request.getImagens());
		
		produto.associa(links);
		em.merge(produto);
		
		URI uri = uriBuilder.path("/3").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(produto.toString());
	}
	
	
	
}
