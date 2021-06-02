package br.com.zupacademy.rodrigo.mercadolivre.detalhe;

import br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica.Caracteristica;

public class DetalheProdutoCaracteristica {

	private String nome;
	private String descricao;

	public DetalheProdutoCaracteristica(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
