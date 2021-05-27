package br.com.zupacademy.rodrigo.mercadolivre.produto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica.CategoriaRepository;
import br.com.zupacademy.rodrigo.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	public void save(@RequestBody @Valid ProdutoDto produtoDto, @AuthenticationPrincipal Usuario usuario) {
		produtoRepository.save(produtoDto.toModel(categoriaRepository, usuario));
	}
}
