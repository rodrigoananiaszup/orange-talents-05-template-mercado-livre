package br.com.zupacademy.rodrigo.mercadolivre.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { UniqueValueValidator.class }) //especifica a classe que efetivara a validação
@Target(ElementType.FIELD) //marca em que tipo de elemento sera aplicado nossa anotação customizada, neste caso é no tipo campo
@Retention(RetentionPolicy.RUNTIME) //efetiva nossa validação em tempo de execução.
public @interface UniqueValue {
	String message() default "Error Not Unique: O valor informado deve ser único!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String fieldName();

	Class<?> domainClass();
}
