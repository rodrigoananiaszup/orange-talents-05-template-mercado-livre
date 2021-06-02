package br.com.zupacademy.rodrigo.mercadolivre.detalhe;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;

public class DetalheProdutoDto {

	private String descricao;
	private String nome;
	private BigDecimal valor;
	private Set<DetalheProdutoCaracteristica> caracteristica;
	private Set<String> urlImagem;
	private Set<String> pergunta;
	private Set<Map<String, String>> opiniao;
	private double mediaNota;

	public DetalheProdutoDto(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.caracteristica = produto.getCaracteristica().stream()
				.map(caracteristica -> new DetalheProdutoCaracteristica(caracteristica)).collect(Collectors.toSet());
		this.pergunta = produto.mapeiaPergunta(pergunta -> pergunta.getTitulo());
		this.opiniao = produto.mapeiaOpiniao(opiniao -> {
			return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});

		Set<Integer> notas = produto.mapeiaOpiniao(opiniao -> opiniao.getNota());
		OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
	
		this.mediaNota = possivelMedia.orElseGet(() -> 0.0);

	}

	public double getMediaNota() {
		return mediaNota;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<String> getUrlImagem() {
		return urlImagem;
	}

	public void setLinkImagem(Set<String> urlImagem) {
		this.urlImagem = urlImagem;
	}

	public Set<DetalheProdutoCaracteristica> getCaracteristica() {
		return caracteristica;
	}

	public Set<String> getPergunta() {
		return pergunta;
	}

	public Set<Map<String, String>> getOpiniao() {
		return opiniao;
	}

}
