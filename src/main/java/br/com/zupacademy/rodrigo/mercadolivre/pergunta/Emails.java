package br.com.zupacademy.rodrigo.mercadolivre.pergunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Emails {

	@Autowired
	private Mailer mailer;

	public void novaPergunta(PerguntaProduto pergunta) {

		mailer.send("<html>...</html>", "Nova Pergunta...", pergunta.getUsuario().getLogin(), "pergunta@zup.com.br",
				pergunta.getProduto().getUsuario().getLogin());

	}
}
