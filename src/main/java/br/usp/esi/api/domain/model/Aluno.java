package br.usp.esi.api.domain.model;

import java.time.LocalDate;

import br.usp.esi.api.domain.dto.RegisterDTO;
import br.usp.esi.api.domain.enums.TipoCurso;
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

@Entity(name = "Aluno")
@Table(name = "alunos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private TipoCurso tipoCurso;

    private String nome;
    private String email;
    private String nusp;
    private String rg;
    private String nomeOrientador;
    private String localNascimento;
    private String nacionalidade;
    private String linkLattes;

    private LocalDate dataNascimento;
    private LocalDate dataUltimaAttLattes;
    private LocalDate dataMatricula;
    private LocalDate dataAprovacaoExameQualificacao;
    private LocalDate dataAprovacaoExameProficiencia;
  
    //private List<String> disciplinasAprovadas;
    //private List<String> disciplinasReprovadas;

    public Aluno(RegisterDTO dto, User user) {
        this.user = user;
        this.tipoCurso = dto.tipoCurso();
        this.nome = dto.nomeUsuario();
        this.email = dto.email();
        this.nusp = dto.nusp();
        this.rg = dto.rg();
        this.nomeOrientador = dto.nomeOrientador();
        this.localNascimento = dto.localNascimento();
        this.nacionalidade = dto.nacionalidade();
        this.linkLattes = dto.linkLattes();
        this.dataNascimento = dto.dataNascimento();
        this.dataUltimaAttLattes = dto.dataUltimaAttLattes();
        this.dataMatricula = dto.dataMatricula();
        this.dataAprovacaoExameQualificacao = dto.dataAprovacaoExameQualificacao();
        this.dataAprovacaoExameProficiencia = dto.dataAprovacaoExameProficiencia();
        //this.disciplinasAprovadas = dto.disciplinasAprovadas();
        //this.disciplinasReprovadas = dto.disciplinasReprovadas();
    }
}