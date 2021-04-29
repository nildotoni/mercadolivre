package br.com.zup.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.dto.Login;
import br.com.zup.mercadolivre.dto.TokenDto;
import br.com.zup.mercadolivre.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticacao(@Valid @RequestBody Login login) {
		UsernamePasswordAuthenticationToken dadoslogin = login.converter();
		try {
			Authentication auth = authManager.authenticate(dadoslogin);
			String token = tokenService.gerarToken(auth);

			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e){
			
			return ResponseEntity.badRequest().build();
				
		}
		
	}
	
}
