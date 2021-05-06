package br.com.zup.mercadolivre.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.mercadolivre.model.Opiniao;

@Repository
public interface OpiniaoRepository extends JpaRepository<Opiniao,Long> {

	Set<Opiniao> findAllByProduto_id(Long id);


}
