package br.com.zupacademy.rodrigo.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.rodrigo.mercadolivre.categoria.Categoria;
import br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica.CaracteristicaDto;
import br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica.CategoriaRepository;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;
import br.com.zupacademy.rodrigo.mercadolivre.validacao.ExistId;

public class ProdutoDto {

	@NotBlank
	private String nome;

	@Positive
	private BigDecimal qtdeDisponivel;

	@NotBlank
	@Length(max = 1000)
	private String descricao;

	@NotNull
	@Positive
	private BigDecimal valor;

	@NotNull
	@Column(nullable = true)
	@PastOrPresent
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@NotNull
	@ExistId(domainClass = Categoria.class, fieldName = "id", message = "categoria não cadastrada")
	private Long categoria;

	@NotEmpty
	private Set<CaracteristicaDto> caracteristica;

	public String getNome() {
		return nome;
	}

	public BigDecimal getQtdeDisponivel() {
		return qtdeDisponivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Long getCategoria() {
		return categoria;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Set<CaracteristicaDto> getCaracteristica() {
		return caracteristica;
	}

	public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
		Optional<Categoria> categoria = categoriaRepository.findById(this.categoria);

		return new Produto(this.nome, this.valor, this.qtdeDisponivel, this.descricao, categoria.get(),
				this.dataCriacao, this.caracteristica, usuario);

	}

}
