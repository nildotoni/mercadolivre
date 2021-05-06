package br.com.zup.mercadolivre.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.compra.CompraForm;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

@RestController
public class CompraController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private EmailCompra email;
	
	@PostMapping("/produto/{id}/compra")
	@Transactional
	public  ModelAndView compraFaseUm(@Valid @PathVariable("id")Long id, @AuthenticationPrincipal Usuario comprador,@RequestBody  CompraForm compra,UriComponentsBuilder uriBuilder){
		Produto produto = em.find(Produto.class, id);

		System.out.println("antes de abater: " + produto.getQuantidade());
		Compra compraIniciada = compra.toModel(produto,comprador);
		compraIniciada.abateEstoque();
		System.out.println("abatido: " + produto.getQuantidade());
		em.merge(compraIniciada);
		Compra compraCadastrada = em.find(Compra.class,compraIniciada.getId());
		if(compraCadastrada != null) {
			compraIniciada.realizaCompra(email);
		}
		String uri = uriBuilder.path(compraIniciada.getMeioPagamento().getUrl() + compraIniciada.getMeioPagamento().getUrlReturn()).buildAndExpand(compraIniciada.getId(),compraIniciada.getId()).toString();
		return  new ModelAndView("redirect:"+ uri);

	}
	
}
