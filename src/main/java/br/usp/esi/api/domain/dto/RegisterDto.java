package br.usp.esi.api.domain.dto;

import java.time.LocalDate;

import java.util.List;

import br.usp.esi.api.domain.enums.TipoCurso;
import br.usp.esi.api.domain.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterDTO(

    @NotBlank(message = "username nao pode ser nulo") @Email
    String username,

    @NotBlank(message = "senha nao pode ser nula")
    String password,

    @NotNull(message = "tipo de usuario nao pode ser nulo")
    UserRole role,
    
    //dados opcionais (aluno, orientador e ccp)
    TipoCurso tipoCurso,
    String nomeUsuario,

    @Email
    String email,

    @Pattern(regexp = "^.{8}$", message = "O número USP deve ter 8 dígitos")
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
    LocalDate dataAprovacaoExameProficiencia,
    List<String> disciplinasAprovadas,
    List<String> disciplinasReprovadas
    ) {}