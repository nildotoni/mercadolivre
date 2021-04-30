package br.com.zup.mercadolivre.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.mercadolivre.model.Caracteristica;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Long>{

}
