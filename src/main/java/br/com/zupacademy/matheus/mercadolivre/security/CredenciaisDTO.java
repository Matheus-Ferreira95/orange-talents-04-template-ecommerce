package br.com.zupacademy.matheus.mercadolivre.security;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CredenciaisDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public CredenciaisDTO(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
        this.email = login;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
