package br.com.zupacademy.rodrigo.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zupacademy.rodrigo.mercadolivre.validacao.UniqueValue;

public class UsuarioDto {

	@Email
	@NotEmpty
	@UniqueValue(domainClass = Usuario.class, fieldName = "login", message = "Este login ja cadastrado em nosso sistema")
	private String login;

	@NotNull
	@Length(min = 6, message = "A senha deve ter no minimo 6 caracteres")
	private String senha;

	@PastOrPresent
	private LocalDateTime dataCriacao = LocalDateTime.now();

	public UsuarioDto(@Email @NotBlank String login,
			@NotNull @Length(min = 6, message = "A senha deve ter no minimo 6 caracteres") String senha,
			@PastOrPresent LocalDateTime dataCriacao) {
		this.login = login;
		this.senha = senha;
		this.dataCriacao = dataCriacao;
	}

	public Usuario toModel() {
		return new Usuario(this.login, new BCryptPasswordEncoder().encode(this.senha));
	}

}
