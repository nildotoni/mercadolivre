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

import br.com.zup.mercadolivre.dto.usuarioForm;
import br.com.zup.mercadolivre.model.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastroUsuario(@Valid @RequestBody usuarioForm form, UriComponentsBuilder uriBuilder){
		Usuario usuario = form.converter();
		manager.persist(usuario);
		URI uri = uriBuilder.path("/CadastroUsuario/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
	}

}
