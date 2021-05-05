package br.com.zupacademy.matheus.mercadolivre.produto;

import br.com.zupacademy.matheus.mercadolivre.caracteristica.ProibeCaracteristicaComNomeIgualValidator;
import br.com.zupacademy.matheus.mercadolivre.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

   @PersistenceContext
   private EntityManager manager;

   @InitBinder
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
}
