package br.com.zupacademy.matheus.mercadolivre.produto;

import br.com.zupacademy.matheus.mercadolivre.produto.caracteristica.NovaCaracteristicaRequest;
import br.com.zupacademy.matheus.mercadolivre.categoria.Categoria;
import br.com.zupacademy.matheus.mercadolivre.compartilhado.validacao.ExistsId;
import br.com.zupacademy.matheus.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @Positive
    @NotNull
    private Integer quantidade;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @ExistsId(klass = Categoria.class)
    private Long idCategoria;

    @Size(min = 3)
    @NotNull
    private List<@Valid NovaCaracteristicaRequest> caracteristicas;

    public ProdutoRequest(@NotBlank String nome, @Positive @NotNull Integer quantidade, @NotBlank @Length(max = 1000)
            String descricao, @NotNull @Positive BigDecimal valor, @NotNull Long idCategoria
            , @Size(min = 3) @NotNull List<NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.caracteristicas = caracteristicas;
    }

    public List<NovaCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas; // necessario por causa do jackson ler os atributos da lista e fazer as validações
    }

    public Produto toModel(EntityManager manager, Usuario usuario) {
        Categoria categoria = manager.find(Categoria.class, 1L);
        return new Produto(nome, quantidade, descricao, valor, categoria, usuario, caracteristicas);
    }

    public Set<String> buscaCaracteristicasIguais() {
        Set<String> nomesIguais = new HashSet<>();
        Set<String> resultados = new HashSet<>();
        for (NovaCaracteristicaRequest caracteristica : caracteristicas) {
            if (resultados.contains(caracteristica.getNome())) {
                nomesIguais.add(caracteristica.getNome());
            }
            resultados.add(caracteristica.getNome());
        }
        return nomesIguais;
    }
}
