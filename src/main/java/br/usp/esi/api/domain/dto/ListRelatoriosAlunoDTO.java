package br.usp.esi.api.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.usp.esi.api.domain.enums.SituacaoRelatorio;
import br.usp.esi.api.domain.model.Relatorio;

public record ListRelatoriosAlunoDTO(
     
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataLimite,
    String nomeRelatorio,
    String nomeAluno,
    String nomeCcp,
    SituacaoRelatorio situacaoRelatorio) {

        public ListRelatoriosAlunoDTO(Relatorio relatorio) {
            this(relatorio.getDataLimite(), relatorio.getNomeRelatorio(), relatorio.getAluno().getNome(), relatorio.getCcp().getNome(), relatorio.getSituacaoRelatorio());
        }
}