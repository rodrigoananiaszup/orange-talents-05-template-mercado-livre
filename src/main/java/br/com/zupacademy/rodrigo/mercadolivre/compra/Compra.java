package br.com.zupacademy.rodrigo.mercadolivre.compra;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.rodrigo.mercadolivre.gateway.Gateway;
import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

@Entity
@Table(name = "compra")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	@Valid
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@Positive
	private Integer qtd;

	@ManyToOne
	@Valid
	@JoinColumn(name = "usuario_id")
	private Usuario user;

	@Enumerated(EnumType.STRING)
	@NotNull
	private Gateway gateway;

	private StatusCompra statusCompra;

	public Compra(Produto produto, Integer qtd, Usuario user, Gateway gateway) {
		this.produto = produto;
		this.qtd = qtd;
		this.user = user;
		this.gateway = gateway;
		this.statusCompra = StatusCompra.INICIADO;
	}

	@SuppressWarnings("unused")
	@Deprecated
	private Compra() {
	}

	public Long getId() {
		return this.id;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public Integer getQtd() {
		return this.qtd;
	}

	public Usuario getUser() {
		return this.user;
	}

	public Gateway getGateway() {
		return this.gateway;
	}

	public StatusCompra getStatusCompra() {
		return this.statusCompra;
	}

	@Override
	public String toString() {
		return "Compra [gateway=" + gateway + ", id=" + id + ", produto=" + produto + ", qtd=" + qtd + ", user=" + user
				+ "]";
	}

}