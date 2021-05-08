package br.com.zupacademy.matheus.mercadolivre.produto.detalhe;

import br.com.zupacademy.matheus.mercadolivre.produto.Produto;
import br.com.zupacademy.matheus.mercadolivre.produto.caracteristica.CaracteristicaProdutoResponse;
import br.com.zupacademy.matheus.mercadolivre.produto.imagem.ImagemProdutoResponse;
import br.com.zupacademy.matheus.mercadolivre.produto.opiniao.OpiniaoResponse;
import br.com.zupacademy.matheus.mercadolivre.produto.pergunta.PerguntaResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalheProdutoResponse {

    private Set<ImagemProdutoResponse> imagens;

    private String nome;

    private BigDecimal preco;

    private Set<CaracteristicaProdutoResponse> caracteristicas;

    private String descricao;

    private Double mediaNotas;

    private Integer totalDeNotas;

    private List<OpiniaoResponse> opinioes;

    private List<PerguntaResponse> perguntas;

    public DetalheProdutoResponse(Produto produto) {
        imagens = produto.getImagens().stream().map(imagemProduto -> new ImagemProdutoResponse(imagemProduto.getLink())).collect(Collectors.toSet());
        caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicaProdutoResponse::new).collect(Collectors.toSet());
        opinioes = produto.getOpinioes().stream().map(OpiniaoResponse::new).collect(Collectors.toList());
        perguntas = produto.getPerguntas().stream().map(PerguntaResponse::new).collect(Collectors.toList());
        nome = produto.getNome();
        preco = produto.getValor();
        descricao = produto.getDescricao();
        totalDeNotas = opinioes.size();
        mediaNotas = produto.calculaMediaAvaliacao();
    }

    public Set<ImagemProdutoResponse> getImagens() {
        return imagens;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<CaracteristicaProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getTotalDeNotas() {
        return totalDeNotas;
    }

    public List<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaResponse> getPerguntas() {
        return perguntas;
    }
}
