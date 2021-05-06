package br.com.zup.mercadolivre.dto;


public class EmailDto {
	private String para;
	private String assunto;
	private String corpo;
	
	
	public void novo(String vendedor, String produto,String comprador) {
		this.para = "Email para: " + vendedor;
		this.assunto = "Venda realizada do produto " + produto;
		this.corpo = "O produto " + produto + " Foi vendido para o comprador " + comprador;
		
		
	}


	public String getPara() {
		return para;
	}


	public String getAssunto() {
		return assunto;
	}


	public String getCorpo() {
		return corpo;
	}


	
	
}
