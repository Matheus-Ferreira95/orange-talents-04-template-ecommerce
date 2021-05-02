package br.com.zupacademy.matheus.mercadolivre.usuario;

import br.com.zupacademy.matheus.mercadolivre.usuario.util.SenhaLimpa;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    private LocalDateTime instanteCadastro;

    @Deprecated
    public Usuario(){}

    public Usuario(String login, SenhaLimpa senhaLimpa) {
        this.login = login;
        this.senha = senhaLimpa.encodeSenha();
    }
}
