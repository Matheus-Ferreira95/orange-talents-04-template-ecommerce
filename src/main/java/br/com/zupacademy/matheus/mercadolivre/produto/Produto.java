package br.com.zupacademy.matheus.mercadolivre.produto;

import br.com.zupacademy.matheus.mercadolivre.produto.caracteristica.CaracteristicaProduto;
import br.com.zupacademy.matheus.mercadolivre.produto.caracteristica.NovaCaracteristicaRequest;
import br.com.zupacademy.matheus.mercadolivre.categoria.Categoria;
import br.com.zupacademy.matheus.mercadolivre.produto.imagem.ImagemProduto;
import br.com.zupacademy.matheus.mercadolivre.produto.opiniao.Opiniao;
import br.com.zupacademy.matheus.mercadolivre.produto.pergunta.Pergunta;
import br.com.zupacademy.matheus.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private String descricao;

    @CreationTimestamp
    private LocalDateTime instanteCadastro;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Usuario dono;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Pergunta> perguntas = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Opiniao> opinioes = new ArrayList<>();

    @Deprecated
    public Produto() {}

    public Produto(@NotBlank String nome, @Positive @NotNull Integer quantidade, @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Positive BigDecimal valor, Categoria categoria, Usuario usuario,
                   @Size(min = 3) @NotNull List<@Valid NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.dono = usuario;
        Set<CaracteristicaProduto> novasCaracteristicas = caracteristicas.stream()
                .map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet());
        this.caracteristicas.addAll(novasCaracteristicas);

        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 caracteristicas");
    }

    public Usuario getDono() {
        return dono;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", instanteCadastro=" + instanteCadastro +
                ", categoria=" + categoria +
                ", dono=" + dono +
                ", caracteristicas=" + caracteristicas +
                '}';
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario possivelDono) {
        return dono.equals(possivelDono);
    }

    public void associaPergunta(Pergunta pergunta) {
        perguntas.add(pergunta);
    }

    public double calculaMediaAvaliacao() {
        return opinioes.stream()
                .mapToDouble(opiniao -> opiniao.getNota())
                .average().orElse(0.0);
    }
}
