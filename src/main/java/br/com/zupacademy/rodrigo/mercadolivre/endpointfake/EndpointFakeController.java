package br.com.zupacademy.rodrigo.mercadolivre.endpointfake;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class EndpointFakeController {

	@PostMapping(value="/nf")
    public void geraNF(@RequestBody NotaFiscalRequest request) throws InterruptedException {
        System.out.println("Gerando Nf para a compra de id: " + request.getIdCompra() + ", de usuario id: " + request.getIdUsuario());
        Thread.sleep(150);
    }

    @PostMapping(value="/ranking")
    public void atualizaRanking(@RequestBody RankingRequest request) throws InterruptedException {
        System.out.println("Atualizando Ranking: " + request.getIdCompra() + ", de Vendedor id: " + request.getIdVendedor());
        Thread.sleep(150);
    }
}
