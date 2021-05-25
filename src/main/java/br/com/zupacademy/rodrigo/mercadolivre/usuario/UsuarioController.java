package br.com.zupacademy.rodrigo.mercadolivre.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	// Encapsulamento
	@PersistenceContext
	private EntityManager em;

	@PostMapping
	@Transactional
	public String cadastrar(@RequestBody @Valid UsuarioDto usuarioDto) {
		Usuario usuario = usuarioDto.toModel();
		em.persist(usuario);

		return usuario.toString();
	}

}
