package br.com.zupacademy.matheus.mercadolivre.produto.opiniao;

public class OpiniaoResponse {

    private String titulo;

    private String descricao;

    public OpiniaoResponse(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
