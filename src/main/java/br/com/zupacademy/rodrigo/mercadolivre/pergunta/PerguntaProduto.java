package br.com.zupacademy.rodrigo.mercadolivre.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

@Entity
@Table(name = "perguntas")
public class PerguntaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titulo;
	private LocalDateTime data = LocalDateTime.now();
	@ManyToOne
	@NotNull
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@ManyToOne
	@NotNull
	@JoinColumn(name = "produto_id")
	private Produto produto;

	public PerguntaProduto(String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {

		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}
	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getData() {
		return data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

}
