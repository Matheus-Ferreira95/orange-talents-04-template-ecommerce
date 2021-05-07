package br.com.zupacademy.matheus.mercadolivre.produto;

import br.com.zupacademy.matheus.mercadolivre.produto.caracteristica.ProibeCaracteristicaComNomeIgualValidator;
import br.com.zupacademy.matheus.mercadolivre.produto.imagem.NovasImagensRequest;
import br.com.zupacademy.matheus.mercadolivre.produto.imagem.Uploader;
import br.com.zupacademy.matheus.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Uploader uploaderFake;

    @InitBinder(value = "novoProdutoRequest")
    public void init(WebDataBinder binder) {
        binder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest,
                                            @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = produtoRequest.toModel(manager, usuarioLogado);
        manager.persist(produto);
        return ResponseEntity.ok(produto.toString());
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public void cadastrar(@PathVariable Long id, @Valid NovasImagensRequest request,
                          @AuthenticationPrincipal Usuario usuarioLogado) {
        Set<String> links = uploaderFake.envia(request.getImagens());
        Produto produto = manager.find(Produto.class, id);

        if(produto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (!produto.pertenceAoUsuario(usuarioLogado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        produto.associaImagens(links);
        manager.merge(produto);
    }
}
