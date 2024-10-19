package br.usp.esi.api.domain.dto;

import java.time.LocalDate;

import br.usp.esi.api.domain.enums.TipoCurso;
import br.usp.esi.api.domain.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(

    @NotBlank @Email
    String username,

    @NotBlank
    String password,

    @NotNull
    UserRole role,
    
    //dados aluno (opcionais)
    TipoCurso tipoCurso,
    String nomeUsuario,
    String email,
    String nusp,
    String rg,
    String nomeOrientador,
    String localNascimento,
    String nacionalidade,
    String linkLattes,
    LocalDate dataNascimento,
    LocalDate dataUltimaAttLattes,
    LocalDate dataMatricula,
    LocalDate dataAprovacaoExameQualificacao,
    LocalDate dataAprovacaoExameProficiencia
    //List<String> disciplinasAprovadas,
    //List<String> disciplinasReprovadas
    ) {}