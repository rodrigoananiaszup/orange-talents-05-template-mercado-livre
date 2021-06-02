package br.com.zupacademy.rodrigo.mercadolivre.detalhe;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.rodrigo.mercadolivre.produto.Produto;

@RestController
@RequestMapping("/produto")
public class DetalheProdutoController {


	@PersistenceContext
	private EntityManager em;

	@GetMapping("/{id}")
	public DetalheProdutoDto detalhaProduto(@PathVariable(name = "id") Long id) {

		Produto produto = em.find(Produto.class,id);
		
		return new DetalheProdutoDto(produto);

	}

}
