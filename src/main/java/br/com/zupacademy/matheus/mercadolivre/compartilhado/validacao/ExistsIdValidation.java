package br.com.zupacademy.matheus.mercadolivre.compartilhado.validacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidation implements ConstraintValidator<ExistsId, Long> {

    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsId params) {
        klass = params.klass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) { // se for null é por que o id não era obrigatório.
            return true;
        }
        Boolean existsId = manager.createQuery("SELECT count(t) > 0 FROM " + klass.getName()
                                            + " t WHERE t.id =:value", Boolean.class)
                                            .setParameter("value", value)
                                            .getSingleResult();
        return existsId;
    }
}
