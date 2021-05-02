package br.com.zupacademy.matheus.mercadolivre.usuario.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {

    private final String senha;

    public SenhaLimpa(String senha) {
        this.senha = senha;
    }

    public String encodeSenha() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
