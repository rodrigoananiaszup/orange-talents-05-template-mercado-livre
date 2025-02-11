package br.com.zupacademy.rodrigo.mercadolivre.pergunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.rodrigo.mercadolivre.compra.Compra;

@Component
public class Email {
    @Autowired
    private Mailer mailer;
    public void novaPergunta(PerguntaProduto pergunta){
        
        mailer.enviar("<html>...</html>","Nova Pergunta..." ,pergunta.getUsuario().getLogin(), "perguntas@zup.com.br" ,pergunta.getProduto().getUsuario().getLogin());

    }

    public void novaCompra(Compra compra){
        mailer.enviar("<html>...</html>","Nova Compra..." ,compra.getUser().getLogin(), "comprass@zup.com.br" ,compra.getProduto().getUsuario().getLogin());
    }
    
    public void pagamentoNaoRealizado(Compra compra) {
        mailer.enviar("<html>...</html>","Pagmento Nao foi Aprovado..." ,compra.getProduto().getUsuario().getLogin(), "comprass@zup.com.br" ,compra.getUser().getLogin());
    }
    
    public void pagamentoRealizado(Compra compra) {
        mailer.enviar("<html>...</html>","Pagamento Aprovado..." ,compra.getUser().getLogin(), "comprass@zup.com.br" ,compra.getProduto().getUsuario().getLogin());
    }

}