package br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;

@Entity
@Table(name = "caracteristicas")
public class Caracteristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	@Column(columnDefinition = "text")
	private String descricao;

	@ManyToOne
	private Produto produto;
	
	

	public Caracteristica() {}

	public Caracteristica(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public Caracteristica(CaracteristicaDto caracteristicaDto, Produto produto) {
		this.nome = caracteristicaDto.getNome();
		this.descricao = caracteristicaDto.getDescricao();
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
