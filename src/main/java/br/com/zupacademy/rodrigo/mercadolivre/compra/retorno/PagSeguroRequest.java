package br.com.zupacademy.rodrigo.mercadolivre.compra.retorno;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.gateway.Gateway;
import br.com.zupacademy.rodrigo.mercadolivre.validacao.UniqueValue;

public class PagSeguroRequest {

	@NotBlank
	@UniqueValue(domainClass = Pagamento.class, fieldName = "idTransacao")
	private String idTransacao;
	
	@NotNull
	private StatusPagamento statusPagamento;

	public String getIdTransacao() {
		return this.idTransacao;
	}

	public StatusPagamento getStatusPagamento() {
		return this.statusPagamento;
	}

	@Deprecated
	private PagSeguroRequest() {
	    }

	public PagSeguroRequest(String idTransacao, StatusPagamento statusPagamento) {
	        this.idTransacao = idTransacao;
	        this.statusPagamento = statusPagamento;
	    }

	@Override
	public String toString() {
		return "{" + " idTransacao='" + getIdTransacao() + "'" + ", statusPagamento='" + getStatusPagamento() + "'"
				+ "}";
	}

	public Pagamento toModel(Compra compra) {
		return new Pagamento(this.idTransacao, Gateway.PAGSEGURO, statusPagamento, compra);
	}
}
