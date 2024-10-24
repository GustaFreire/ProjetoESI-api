package br.usp.esi.api.domain.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

import br.usp.esi.api.domain.enums.TipoCurso;
import br.usp.esi.api.domain.model.Aluno;

public record AlunoDetalhamentoDTO(
    Long idAluno,
    Long idUser,
    TipoCurso tipoCurso,

    String nomeUsuario,
    String email,
    String nusp,
    String rg,
    String nomeOrientador,
    String localNascimento,
    String nacionalidade,
    String linkLattes,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataNascimento,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataUltimaAttLattes,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataMatricula,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataAprovacaoExameQualificacao,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataAprovacaoExameProficiencia,
    
    List<String> disciplinasAprovadas,
    List<String> disciplinasReprovadas) {

    public AlunoDetalhamentoDTO(Aluno aluno, List<String> disciplinasAprovadas,
        List<String> disciplinasReprovadas) {
        
        this(aluno.getId(), 
        aluno.getUser().getId(), 
        aluno.getTipoCurso(), 
        aluno.getNome(), 
        aluno.getEmail(), 
        aluno.getNusp(), 
        aluno.getRg(), 
        aluno.getNomeOrientador(), 
        aluno.getLocalNascimento(), 
        aluno.getNacionalidade(), 
        aluno.getLinkLattes(), 
        aluno.getDataNascimento(), 
        aluno.getDataUltimaAttLattes(), 
        aluno.getDataMatricula(), 
        aluno.getDataAprovacaoExameQualificacao(), 
        aluno.getDataAprovacaoExameProficiencia(),
        disciplinasAprovadas, 
        disciplinasReprovadas);
    }
}