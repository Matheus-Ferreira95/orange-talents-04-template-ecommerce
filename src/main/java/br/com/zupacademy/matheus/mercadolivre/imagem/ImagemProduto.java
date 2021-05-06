package br.com.zupacademy.matheus.mercadolivre.imagem;

import br.com.zupacademy.matheus.mercadolivre.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    @Column(nullable = false)
    private String link;

    @Deprecated
    public ImagemProduto() {}

    public ImagemProduto(Produto produto, @URL String link) {
        this.produto = produto;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return Objects.equals(produto, that.produto) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, link);
    }
}
