package br.com.zupacademy.rodrigo.mercadolivre.pergunta;

public interface Mailer {
	void send(String body, String subject, String nameFrom, String from, String nameTo);
}
