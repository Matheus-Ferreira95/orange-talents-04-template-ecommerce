package br.com.zupacademy.matheus.mercadolivre.produto.caracteristica;

import br.com.zupacademy.matheus.mercadolivre.produto.Produto;

import javax.validation.constraints.NotBlank;

public class NovaCaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public NovaCaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {            // os getters são necessários para que o jackson possa desserializar e validar corretamente
        return nome;                        // os objetos da lista
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProduto toModel(Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }
}
