package br.com.zupacademy.matheus.mercadolivre.produto.opiniao;

import br.com.zupacademy.matheus.mercadolivre.produto.Produto;
import br.com.zupacademy.matheus.mercadolivre.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Validated
public class OpiniaoController {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping("/produtos/{id}/opinioes")
    public ResponseEntity<String> cadastrarOpiniao(@PathVariable Long id,
                                             @RequestBody @Valid NovaOpiniaoRequest request,
                                             @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = manager.find(Produto.class, id);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        Opiniao opiniao = request.toModel(produto, usuarioLogado);
        manager.persist(opiniao);

        return ResponseEntity.ok(opiniao.toString());
    }
}
