package br.com.zupacademy.rodrigo.mercadolivre.produto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica.CategoriaRepository;
import br.com.zupacademy.rodrigo.mercadolivre.produto.imagem.Imagem;
import br.com.zupacademy.rodrigo.mercadolivre.produto.imagem.ImagemDto;
import br.com.zupacademy.rodrigo.mercadolivre.produto.imagem.ImagemRepository;
import br.com.zupacademy.rodrigo.mercadolivre.produto.imagem.SalvaImagem;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ImagemRepository imagemRepository;
	
	@Autowired
	private SalvaImagem salvaImagem;

	@PostMapping
	public void save(@RequestBody @Valid ProdutoDto produtoDto, @AuthenticationPrincipal Usuario usuario) {
		produtoRepository.save(produtoDto.toModel(categoriaRepository, usuario));
	}

	@PostMapping("/{id}/imagem")
	@Transactional
	public ResponseEntity<Object> adicionaImagem(@PathVariable(name = "id") Long id, @Valid ImagemDto imagemDto,
			@AuthenticationPrincipal Usuario usuario) {

		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isPresent()) {
			Produto produto = optional.get();

			if (usuario.getId() != produto.getUsuario().getId()) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}

			Set<String> links = salvaImagem.enviaImagem(imagemDto.getImagens());
			List<Imagem> imagens = imagemRepository.saveAll(imagemDto.toModel(produto, links));

			return ResponseEntity.ok().build();

		} else {
			return ResponseEntity.badRequest().build();
		}

	}
}
