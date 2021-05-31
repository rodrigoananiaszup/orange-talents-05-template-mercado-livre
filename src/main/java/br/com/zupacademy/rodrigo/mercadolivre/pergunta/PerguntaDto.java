package br.com.zupacademy.rodrigo.mercadolivre.pergunta;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

public class PerguntaDto {

	@NotNull 
	@NotBlank
	private String titulo;
	private LocalDateTime data;
	private Long idUsuario;
	private Long idProduto;

	private PerguntaDto() {}

	public PerguntaDto(@NotNull @NotBlank String titulo, LocalDateTime data, @NotNull Long idUsuario, @NotNull Long idProduto) {
		super();
		this.titulo = titulo;
		this.data = data;
		this.idUsuario = idUsuario;
		this.idProduto = idProduto;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getdata() {
		return data;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public PerguntaProduto toModel(Usuario usuario, Produto produto) {

		return new PerguntaProduto(this.titulo, usuario, produto);
	}

	public PerguntaDto (PerguntaProduto pergunta) {
		this.titulo = pergunta.getTitulo();
		this.data = pergunta.getData();
		this.idUsuario = pergunta.getUsuario().getId();
		this.idProduto = pergunta.getProduto().getId();
	}

}
