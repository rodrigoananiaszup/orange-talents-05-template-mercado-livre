package br.com.zupacademy.rodrigo.mercadolivre.compra.retorno;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;

@Component
public class NotaFiscal {

	public void gerar(Compra compra) {
        RestTemplate rest = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(),"idUsuario", compra.getUser().getId());

        rest.postForEntity("http://localhost:8080/nf", request, String.class);

    }
}
