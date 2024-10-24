package br.usp.esi.api.domain.enums;

public enum ResultadoUltimaAvaliacao {

    APROVADO("aprovado"),
    APROVADO_COM_RESSALVAS("aprovadoComRessalvas"),
    INSATISFATORIO("insatisfatorio"),
    NAO_SE_APLICA("naoSeAplica");

    private String resultado;

    ResultadoUltimaAvaliacao(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}