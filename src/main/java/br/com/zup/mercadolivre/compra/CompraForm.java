package br.com.zup.mercadolivre.compra;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;



public class CompraForm {
	@NotNull @Positive
	private Integer quantidade;
	@Valid @NotNull
	private Double valor;
	@Valid @NotNull
	private MeioDePagamento gateway;
	
	public Compra toModel(Produto produto, Usuario comprador) {
		return new Compra(produto,comprador,quantidade,gateway);
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public Double getValor() {
		return valor;
	}


	public MeioDePagamento getGateway() {
		return gateway;
	}



	
	
}
