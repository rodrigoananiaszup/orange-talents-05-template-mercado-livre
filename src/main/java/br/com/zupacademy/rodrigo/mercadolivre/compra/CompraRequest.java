package br.com.zupacademy.rodrigo.mercadolivre.compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.rodrigo.mercadolivre.gateway.Gateway;
import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.validacao.ExistId;

public class CompraRequest {

	@Positive
	@NotNull
	private Integer qtde;
	@NotNull
	@ExistId(domainClass = Produto.class, fieldName = "id")
	private Long idProduto;
	@NotNull
	private Gateway gateway;

	public Integer getQtde() {
		return this.qtde;
	}

	public Long getIdProduto() {
		return this.idProduto;
	}

	public Gateway getGateway() {
		return this.gateway;
	}

	public CompraRequest(Integer qtd, Long idProduto, Gateway gateway) {
		this.qtde = qtd;
		this.idProduto = idProduto;
		this.gateway = gateway;
	}

	private CompraRequest() {
	}

	@Override
	public String toString() {
		return "{" + " qtde='" + getQtde() + "'" + ", idProduto='" + getIdProduto() + "'" + "}";
	}

}
