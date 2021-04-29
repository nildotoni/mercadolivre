package br.com.zup.mercadolivre.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.dto.Categoria;
import br.com.zup.mercadolivre.dto.CategoriaForm;
import br.com.zup.mercadolivre.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository cr;
	
	@PostMapping
	public ResponseEntity<?> cadastroCategoria(@Valid @RequestBody CategoriaForm form, UriComponentsBuilder uriBuilder){
		Categoria cat = form.toCategoria(cr);
		cr.save(cat);
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).body(cat);
		
	}
	
}
