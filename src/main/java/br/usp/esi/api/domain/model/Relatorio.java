package br.usp.esi.api.domain.model;

import java.time.LocalDate;

import br.usp.esi.api.domain.enums.ResultadoUltimaAvaliacao;
import br.usp.esi.api.domain.enums.SituacaoRelatorio;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Relatorio")
@Table(name = "relatorios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orientador_id", nullable = false)
    private Orientador orientador;

    private LocalDate dataLimite;//
    private LocalDate prazoMaximoQualificacao;
    private LocalDate prazoMaximoDissertacao;

    private Integer numArtigosEscrita;
    private Integer numArtigosSubmetidos;
    private Integer numArtigosPublicados;

    private String resumoAtividadesAcademicas;
    private String resumoAtividadesPesquisa;
    private String infoAdicionalParaCcp;
    private String nomeRelatorio;

    @Enumerated(EnumType.STRING)
    private ResultadoUltimaAvaliacao resultadoUltimaAvaliacao;
    @Enumerated(EnumType.STRING)
    private SituacaoRelatorio situacaoRelatorio;

    private Boolean necessitaApoioCcp;

    public Relatorio(String nomeRelatorio, LocalDate dataLimite, Aluno aluno) {
        this.dataLimite = dataLimite;
        this.nomeRelatorio = nomeRelatorio;
        this.aluno = aluno;
        this.situacaoRelatorio = SituacaoRelatorio.PENDENTE_ALUNO;
    }
}