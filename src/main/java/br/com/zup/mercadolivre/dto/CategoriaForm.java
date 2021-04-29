package br.com.zup.mercadolivre.dto;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.repository.CategoriaRepository;
import br.com.zup.mercadolivre.validators.Unico;

public class CategoriaForm {

	@Unico(atributo = "nome", classe = Categoria.class)
	@NotNull
	@NotEmpty
	private String nome;

	private Long categoria_id;

	public String getNome() {
		return nome;
	}

	public Long getCategoria_id() {
		return categoria_id;
	}

	public Categoria toCategoria(CategoriaRepository cr) {
		Categoria cat = new Categoria(nome);
		if(categoria_id != null) {
		Optional<Categoria> catMae = cr.findById(categoria_id);
		if (catMae.isPresent()) {
			cat.setCategoria(catMae.get());
		}}
		return cat;

	}

}
