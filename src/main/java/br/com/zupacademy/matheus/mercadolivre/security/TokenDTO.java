package br.com.zupacademy.matheus.mercadolivre.security;

public class TokenDTO {

    private String token;

    public TokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
