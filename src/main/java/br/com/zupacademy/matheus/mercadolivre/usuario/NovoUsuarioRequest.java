package br.com.zupacademy.matheus.mercadolivre.usuario;

import br.com.zupacademy.matheus.mercadolivre.compartilhado.validacao.UniqueValue;
import br.com.zupacademy.matheus.mercadolivre.usuario.util.SenhaLimpa;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {

    @NotBlank
    @Email
    @UniqueValue(field = "login", klass = Usuario.class)
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public NovoUsuarioRequest(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(login, new SenhaLimpa(senha));
    }
}
