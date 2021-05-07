package br.com.zupacademy.matheus.mercadolivre.compartilhado.handler;

public class FieldErrorOutputDto {

    private String campo;
    private String mensagem;

    public FieldErrorOutputDto(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
