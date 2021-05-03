package br.com.zupacademy.matheus.mercadolivre.categoria;

import br.com.zupacademy.matheus.mercadolivre.compartilhado.validacao.ExistsId;
import br.com.zupacademy.matheus.mercadolivre.compartilhado.validacao.UniqueValue;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(field = "nome", klass = Categoria.class)
    private String nome;

    @ExistsId(klass = Categoria.class)
    private Long categoriaMaeId;

    public CategoriaRequest(String nome) {
        this.nome = nome;
    }

    public CategoriaRequest(String nome, Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository) {
        Categoria categoria = new Categoria(nome);
        if (categoriaMaeId != null) {
            Optional<Categoria> categoriaMae = categoriaRepository.findById(categoriaMaeId);
            categoria.setCategoriaMae(categoriaMae.get()); // não precisa verificar se o optional está presente, a validação faz isso pra nós
        }
        return categoria;
    }
}
