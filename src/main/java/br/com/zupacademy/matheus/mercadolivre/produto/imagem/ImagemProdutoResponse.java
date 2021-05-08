package br.com.zupacademy.matheus.mercadolivre.produto.imagem;

public class ImagemProdutoResponse {

    private String urlImagem;

    public ImagemProdutoResponse(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getUrlImagem() {
        return urlImagem;
    }
}
