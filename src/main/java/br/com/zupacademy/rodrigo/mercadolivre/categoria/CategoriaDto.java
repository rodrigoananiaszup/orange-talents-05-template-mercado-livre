package br.com.zupacademy.rodrigo.mercadolivre.categoria;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.rodrigo.mercadolivre.validacao.ExistId;
import br.com.zupacademy.rodrigo.mercadolivre.validacao.UniqueValue;

public class CategoriaDto {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "O nome desta Categoria já está cadastrado")
	private String nome;

	@ManyToOne
	@ExistId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoriaMae;

	public CategoriaDto(Categoria categoria) {

		this.nome = categoria.getNome();
		if (categoria.getCategoriaMae() != null) {
			this.idCategoriaMae = categoria.getCategoriaMae().getId();
		}

	}

	public CategoriaDto(Long idCategoriaMae, String nome) {
		this.idCategoriaMae = idCategoriaMae;
		this.nome = nome;
	}

	public Categoria toModel(EntityManager em) {

		if (this.idCategoriaMae == null) {
			return new Categoria(this.nome, null);
		}

		Categoria categoriamae = em.find(Categoria.class, this.idCategoriaMae);

		return new Categoria(this.nome, categoriamae);
	}
}
