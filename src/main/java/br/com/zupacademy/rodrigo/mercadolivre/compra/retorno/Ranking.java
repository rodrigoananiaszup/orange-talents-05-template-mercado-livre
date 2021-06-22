package br.com.zupacademy.rodrigo.mercadolivre.compra.retorno;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;

@Component
public class Ranking {

	public void atualiza(Compra compra) {
        RestTemplate rest = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", 
        		compra.getId(),"idVendedor", 
        		compra.getProduto().getUsuario().getId());

        rest.postForEntity("http://localhost:8080/ranking", request, String.class);

    }
}
