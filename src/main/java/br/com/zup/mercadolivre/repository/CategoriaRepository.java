package br.com.zup.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.mercadolivre.dto.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

}
