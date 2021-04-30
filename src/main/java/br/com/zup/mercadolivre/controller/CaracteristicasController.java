package br.com.zup.mercadolivre.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.dto.CaracteristicaForm;
import br.com.zup.mercadolivre.model.Caracteristica;

@RestController
@RequestMapping("/caracteristica")
public class CaracteristicasController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastroCaracteristica(@Valid @RequestBody CaracteristicaForm form,UriComponentsBuilder uriBuilder){
		Caracteristica caracteristica = form.converter(entityManager);
		entityManager.persist(caracteristica);
		URI uri = uriBuilder.path("/caracteristica/{id}").buildAndExpand(caracteristica.getId()).toUri();
		return ResponseEntity.created(uri).body(caracteristica);
	}
	
}
