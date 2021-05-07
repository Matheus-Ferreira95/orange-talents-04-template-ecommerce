package br.com.zupacademy.matheus.mercadolivre.produto.pergunta;

import br.com.zupacademy.matheus.mercadolivre.produto.Produto;
import br.com.zupacademy.matheus.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Produto produto, Usuario usuarioLogado) {
        return new Pergunta(titulo, usuarioLogado, produto);
    }
}
