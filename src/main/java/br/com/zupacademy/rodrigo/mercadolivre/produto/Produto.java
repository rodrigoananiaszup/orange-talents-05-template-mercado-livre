package br.com.zupacademy.rodrigo.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.rodrigo.mercadolivre.categoria.Categoria;
import br.com.zupacademy.rodrigo.mercadolivre.opiniao.OpiniaoProduto;
import br.com.zupacademy.rodrigo.mercadolivre.pergunta.PerguntaProduto;
import br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica.CaracteristicaDto;
import br.com.zupacademy.rodrigo.mercadolivre.produto.imagem.Imagem;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

@Entity
@Table(name = "produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;
	@Positive
	private BigDecimal valor;
	@Positive
	private BigDecimal qtdeDisponivel;
	@Length(max = 100, min = 1)
	private String descricao;

	@NotNull
	@Column(nullable = true)
	@PastOrPresent
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@NotNull
	@ManyToOne
	private Categoria categoria;

	@NotEmpty
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
	private Set<Caracteristica> caracteristica;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Imagem> imagens = new HashSet<Imagem>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<PerguntaProduto> pergunta = new HashSet<PerguntaProduto>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<OpiniaoProduto> opiniao = new HashSet<OpiniaoProduto>();


	@ManyToOne
	@NotNull
	Usuario usuario;

	@Deprecated
	public Produto() {
	}

	public Produto(String nome, BigDecimal valor, @Positive BigDecimal qtdeDisponivel, String descricao,
			Categoria categoria, LocalDateTime dataCriacao, Set<CaracteristicaDto> caracteristica, Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.qtdeDisponivel = qtdeDisponivel;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.categoria = categoria;
		this.caracteristica = caracteristica.stream().map(o -> new Caracteristica(o, this)).collect(Collectors.toSet());
		this.usuario = usuario;
	}
	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public Set<Imagem> getImagens() {
		return imagens;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		// TODO Auto-generated method stub
		return valor;
	}

	public Set<Caracteristica> getCaracteristica() {
		// TODO Auto-generated method stub
		return caracteristica;
	}
	
	public String getEmailUsuario() {
		return usuario.getLogin();
	}

	public <T> Set<T> mapeiaImagem(Function<Imagem, T> funcaoMapeadora) {
		return this.imagens.stream().map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}
	
	public <T> Set<T> mapeiaPergunta(Function<PerguntaProduto, T> funcaoMapeadora) {
		return this.pergunta.stream().map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}
	
	public <T> Set<T> mapeiaOpiniao(Function<OpiniaoProduto, T> funcaoMapeadora) {
		return this.opiniao.stream().map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}
	
	public boolean possuiQuantidade(BigDecimal quantidade) {
		return (this.qtdeDisponivel.compareTo(quantidade) == -1 ? false : true);
	}

	public boolean reduzirQuantidade(BigDecimal quantidade) {
		if (possuiQuantidade(quantidade)) {
			this.qtdeDisponivel = qtdeDisponivel.subtract(quantidade);
			return true;
		}
		return false;
	}

	public boolean diminuiQtde(Integer qtd) {
		if(this.qtdeDisponivel.compareTo(new BigDecimal(qtd)) >= 0){
			this.qtdeDisponivel = this.qtdeDisponivel.subtract(new BigDecimal(qtd));
			return true;
		} else {
			return false;
		}
	}


	
	

}
