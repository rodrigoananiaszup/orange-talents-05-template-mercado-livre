package br.com.zupacademy.rodrigo.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	//encapsulamento dos dados
	@PersistenceContext
	EntityManager em;

	@PostMapping
	@Transactional
	public String cadastra(@RequestBody @Valid CategoriaDto categoriaDto) {

		Categoria categoria = categoriaDto.toModel(em);
		em.persist(categoria);

		return categoria.toString();
	}

}