package br.com.zupacademy.matheus.mercadolivre.produto.pergunta;

import br.com.zupacademy.matheus.mercadolivre.usuario.Usuario;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EnviadorDeEmailFake implements EnviadorDeEmail {

    @Override
    public void enviaEmailParaVendedor(Usuario usuario) {
        System.out.println("Simulando email para o vendedor de id " + usuario.getId());
    }
}
