package br.com.zupacademy.rodrigo.mercadolivre.produto.caracteristica;

import org.springframework.data.repository.CrudRepository;

import br.com.zupacademy.rodrigo.mercadolivre.categoria.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long>{}