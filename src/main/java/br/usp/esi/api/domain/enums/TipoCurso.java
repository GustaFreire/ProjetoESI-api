package br.usp.esi.api.domain.enums;

public enum TipoCurso {

    MESTRADO("mestrado"),
    DOUTORADO("doutorado");

    private String tipo;

    TipoCurso(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}