package br.com.zupacademy.rodrigo.mercadolivre.validacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

//validações do @UniqueValue
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object>{

	@PersistenceContext
    EntityManager em;
    private String domainAttribute;
    private Class<?> cClass;

    @Override
    public void initialize(UniqueValue uniqueValue) {
        domainAttribute = uniqueValue.fieldName().toLowerCase();
        cClass = uniqueValue.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = em.createQuery("SELECT 1 FROM " + cClass.getName()
              + " WHERE " + domainAttribute + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        return list.isEmpty();
    }
}