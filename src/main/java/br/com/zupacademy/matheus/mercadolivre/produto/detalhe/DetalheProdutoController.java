package br.com.zupacademy.matheus.mercadolivre.produto.detalhe;

import br.com.zupacademy.matheus.mercadolivre.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
public class DetalheProdutoController {

    @Autowired
    private EntityManager manager;

    @GetMapping("/produtos/{id}")
    public ResponseEntity<DetalheProdutoResponse> detalheProduto(@PathVariable Long id) {
        Produto produto = manager.find(Produto.class, id);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        DetalheProdutoResponse detalhe = new DetalheProdutoResponse(produto);
        return ResponseEntity.ok(detalhe);
    }
}
