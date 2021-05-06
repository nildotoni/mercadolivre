package br.com.zup.mercadolivre.compra;

public  enum MeioDePagamento{
	PAYPAL("paypal.com/?buyerId={id}", "&redirectUrl=/retorno-paypal/{id}")
	, PAGSEGURO("pagseguro.com/?returnId={id}", "&redirectUrl=/retorno-pagseguro/{id}");

	private String url;
	private String urlReturn;
	
	
	MeioDePagamento(String url,String urlReturn) {
		this.url = url;
		this.urlReturn = urlReturn;
	}

	public String getUrl() {
		return url;
	}

	public String getUrlReturn() {
		return urlReturn;
	}
	
	
	
}
