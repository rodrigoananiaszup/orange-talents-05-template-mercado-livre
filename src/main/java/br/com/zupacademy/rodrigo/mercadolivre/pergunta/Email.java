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

}