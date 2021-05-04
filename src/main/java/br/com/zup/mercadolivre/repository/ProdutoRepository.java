package br.com.zup.mercadolivre.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

	Optional<Produto> findById(long id);

}
