package br.com.zup.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.mercadolivre.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

}
