package br.com.zupacademy.rodrigo.mercadolivre.endpointfake;

public class NotaFiscalRequest {

	private Long idCompra;
	private Long idUsuario;

	public Long getIdCompra() {
		return this.idCompra;
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public NotaFiscalRequest(Long idCompra, Long idUsuario) {
		this.idCompra = idCompra;
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "{" + " idCompra='" + getIdCompra() + "'" + ", idUsuario='" + getIdUsuario() + "'" + "}";
	}
}
