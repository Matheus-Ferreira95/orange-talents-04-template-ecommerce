package br.com.zupacademy.matheus.mercadolivre.produto.pergunta;

import br.com.zupacademy.matheus.mercadolivre.produto.Produto;
import br.com.zupacademy.matheus.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PerguntaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private EnviadorDeEmail enviadorDeEmail;

    @PostMapping("/produtos/{id}/perguntas")
    @Transactional
    public ResponseEntity<String> cadastroPergunta(@PathVariable Long id,
                                                   @RequestBody @Valid NovaPerguntaRequest request,
                                                   @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = manager.find(Produto.class, id);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        Pergunta pergunta = request.toModel(produto, usuarioLogado);
        produto.associaPergunta(pergunta);
        enviadorDeEmail.enviaEmailParaVendedor(produto.getDono());
        manager.merge(produto);

        return ResponseEntity.ok("kkkkk");
    }
}
