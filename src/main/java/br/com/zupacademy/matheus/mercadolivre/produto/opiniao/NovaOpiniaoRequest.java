package br.com.zupacademy.matheus.mercadolivre.produto.opiniao;

import br.com.zupacademy.matheus.mercadolivre.produto.Produto;
import br.com.zupacademy.matheus.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

public class NovaOpiniaoRequest {

    @Range(min = 1, max = 5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    public NovaOpiniaoRequest(@Range(min = 1, max = 5) int nota, @NotBlank String titulo,
                              @NotBlank @Length(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(Produto produto, Usuario usuarioLogado) {
        return new Opiniao(nota, titulo, descricao, usuarioLogado, produto);
    }
}
