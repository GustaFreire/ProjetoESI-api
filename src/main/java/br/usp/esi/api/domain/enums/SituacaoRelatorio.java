package br.usp.esi.api.domain.enums;

public enum SituacaoRelatorio {

    PENDENTE_ALUNO("pendenteAluno"),
    PENDENTE_ORIENTADOR("pendenteOrientador"),
    PENDENTE_CCP("pendenteCcp"),
    SEM_PENDENCIAS("semPendencias");

    private String situacao;

    SituacaoRelatorio(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }
}