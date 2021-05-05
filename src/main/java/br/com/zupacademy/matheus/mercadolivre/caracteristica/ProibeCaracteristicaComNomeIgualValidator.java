package br.com.zupacademy.matheus.mercadolivre.caracteristica;

import br.com.zupacademy.matheus.mercadolivre.produto.ProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()){
            return;
        }

        ProdutoRequest request = (ProdutoRequest) o;
        Set<String> nomesIguais = request.buscaCaracteristicasIguais();
        if (!nomesIguais.isEmpty()) {
            errors.rejectValue("caracteristicas", null, "Caracteristicas repitidas " + nomesIguais);
        }

    }
}
