package br.com.zupacademy.rodrigo.mercadolivre.opiniao;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;
import br.com.zupacademy.rodrigo.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

@RestController
public class OpiniaoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private OpiniaoProdutoRepository opiniaoProdutoRepository;

	@PostMapping("/produto/{id}/opiniao")
	public ResponseEntity<Object> adicionaOpiniao(@PathVariable(name = "id") Long id,
			@Valid @RequestBody OpiniaoDto opiniaoDto, @AuthenticationPrincipal Usuario usuario,
			UriComponentsBuilder builder) {

		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isPresent()) {
			Produto produto = optional.get();
			OpiniaoProduto opiniaoProduto = this.opiniaoProdutoRepository.save(opiniaoDto.toModel(produto, usuario));
			URI uri = builder.path("/produto/" + id + "/opiniao/{id}").buildAndExpand(opiniaoProduto.getId())
					.toUri();

			return ResponseEntity.ok((new OpiniaoDto(opiniaoProduto)));

		} else {
			return ResponseEntity.notFound().build();
		}

	}
}
