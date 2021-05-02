package br.com.zupacademy.matheus.mercadolivre.compartilhado.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UniqueValueValidation.class})
@Target({FIELD, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface UniqueValue {

    String message() default "Dado já consta como cadastrado.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field();

    Class<?> klass();
}