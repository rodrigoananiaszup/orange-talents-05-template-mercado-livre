package br.com.zupacademy.rodrigo.mercadolivre.compra.retorno;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;
import br.com.zupacademy.rodrigo.mercadolivre.compra.CompraRepository;
import br.com.zupacademy.rodrigo.mercadolivre.compra.StatusCompra;
import br.com.zupacademy.rodrigo.mercadolivre.pergunta.Email;

@RestController
@RequestMapping("/compras/retorno/pagseguro")
public class PagSeguroController {

	@Autowired
    private CompraRepository compraRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private Email emails;
    @Autowired
    private NotaFiscal notaFiscal;
    @Autowired
    private Ranking ranking;


    @PostMapping("/{idCompra}")
    @Transactional
    public String confirmaPagamento(@PathVariable(name = "idCompra") Long idCompra, @RequestBody @Valid PagSeguroRequest pagamentoRequest) throws BindException{
        Optional<Compra> optional = compraRepository.findById(idCompra);
        if(optional.isPresent()){
            Compra compra = optional.get();
            if(compra.getStatusCompra().equals(StatusCompra.FINALIZADA)){
                BindException compraFinalizada = new BindException(pagamentoRequest, "pagamentoRequest");
                compraFinalizada.reject("2", "Compra ja se encontra FINALIZADA");
                throw compraFinalizada;
            }
            Pagamento pagamento = pagamentoRepository.save(pagamentoRequest.toModel(compra));
            boolean statusCompra = compra.atualizaStatus(pagamento);
            if(statusCompra){
                notaFiscal.gerar(compra);
                ranking.atualiza(compra);
                emails.pagamentoRealizado(compra);
            } else {
                emails.pagamentoNaoRealizado(compra);
            }

            return pagamento.toString();
        } else{
            return pagamentoRequest.toString();
        }


    }
}
