package br.com.zup.mercadolivre.compra;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.controller.EmailCompra;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

@Entity
public class Compra {
	
	@Id @Valid
	private UUID id = UUID.randomUUID();
	@ManyToOne
	private Produto produto;
	@NotNull @Positive
	private Integer quantidade;
	@ManyToOne
	private Usuario comprador;
	@Valid @NotNull
	private Double valor;
	@Valid @NotNull
	private MeioDePagamento meioPagamento;
	
	private StatusCompra status;

	
	public Compra(@Valid @NotNull Produto produto, @Valid @NotNull Usuario comprador,
			@NotNull @Positive Integer quantidade, 
			@Valid @NotNull MeioDePagamento meioPagamento) {
	
		this.produto = produto;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.valor = produto.getValor();
		this.meioPagamento = meioPagamento;
		
	}
	
	@Deprecated
	public Compra() {
		
	}
	
	public String redirect() {
		String url = meioPagamento.getUrl();
		return url;
	}
	
	public Boolean abateEstoque() {
		Integer valorTotal = produto.getQuantidade() - this.quantidade;
		if (valorTotal < 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
		}
		this.produto.setQuantidade(valorTotal);
		this.status = StatusCompra.INICIADO;
		return true;
	}

	public StatusCompra getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", produto=" + produto.getNome() + ", quantidade=" + quantidade + ", comprador=" + comprador.getEmail()
				+ ", valor=" + valor + ", meioPagamento=" + meioPagamento + ", status=" + status + "]";
	}

	public MeioDePagamento getMeioPagamento() {
		return meioPagamento;
	}

	public Boolean realizaCompra(EmailCompra email) {
		if(status == StatusCompra.INICIADO) {
			
			email.enviaEmail(comprador,produto);
			
			return true;
		}
		return false;
	}

	public UUID getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public Double getValor() {
		return valor;
	}
	
	
}
