package br.com.zupacademy.rodrigo.mercadolivre.compra.retorno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}
