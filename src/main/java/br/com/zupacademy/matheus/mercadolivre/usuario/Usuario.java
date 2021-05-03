package br.com.zupacademy.matheus.mercadolivre.usuario;

import br.com.zupacademy.matheus.mercadolivre.usuario.util.SenhaLimpa;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {

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
        Assert.hasLength(login, "login não pode ser em branco");
        Assert.notNull(senhaLimpa, "O objeto do tipo senhaLimpa não pode ser nulo");

        this.login = login;
        this.senha = senhaLimpa.encodeSenha();
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
