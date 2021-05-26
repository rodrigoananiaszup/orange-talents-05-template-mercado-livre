package br.com.zupacademy.rodrigo.mercadolivre.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistIdValidator implements ConstraintValidator<ExistId, Object>{

	@PersistenceContext
    EntityManager em;
    private String domainAttribute;
    private Class<?> cClass;

    @Override
    public void initialize(ExistId existId) {
        domainAttribute = existId.fieldName().toLowerCase();
        cClass = existId.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }

        Query query = em.createQuery("select 1 from "+cClass.getName()+" where "+domainAttribute+"=:value");
        query.setParameter("value", value);


        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <=1, "algo aconteceu e existe mais de um "+cClass+" com o atributo "+domainAttribute+" com o valor = "+value);

        return !list.isEmpty();
    }

}
