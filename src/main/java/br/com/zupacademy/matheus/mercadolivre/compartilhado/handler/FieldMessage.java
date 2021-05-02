package br.com.zupacademy.matheus.mercadolivre.compartilhado.handler;

public class FieldMessage {

    private String campo;
    private String mensagem;

    public FieldMessage(String campo, String mensagem) {
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
