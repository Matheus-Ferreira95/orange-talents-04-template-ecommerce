package br.com.zupacademy.matheus.mercadolivre.compartilhado.validacao;

import br.com.zupacademy.matheus.mercadolivre.compartilhado.validacao.UniqueValue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidation implements ConstraintValidator<UniqueValue, Object> {

    @PersistenceContext
    private EntityManager manager;

    private String campo;
    private Class<?> klass;

    @Override
    public void initialize(UniqueValue params) {
        campo = params.field();
        klass = params.klass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Boolean uniqueValue = manager.createQuery("SELECT count(t) < 1 FROM " +
                klass.getName() + " t WHERE " + campo + "=:value", Boolean.class)
                .setParameter("value", value)
                .getSingleResult();

        return uniqueValue;
    }
}
