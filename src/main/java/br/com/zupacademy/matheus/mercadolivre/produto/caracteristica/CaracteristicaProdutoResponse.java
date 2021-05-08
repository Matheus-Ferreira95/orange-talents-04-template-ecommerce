package br.com.zupacademy.matheus.mercadolivre.produto.caracteristica;

public class CaracteristicaProdutoResponse {

    private String nome;

    private String descricao;

    public CaracteristicaProdutoResponse(CaracteristicaProduto caracteristicaProduto) {
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
