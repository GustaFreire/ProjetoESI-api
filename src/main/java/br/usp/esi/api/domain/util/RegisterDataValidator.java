package br.usp.esi.api.domain.util;

import java.util.List;
import java.util.ArrayList;

import br.usp.esi.api.domain.dto.SignUpDTO;

public class RegisterDataValidator {

    public static List<String> validateStudentData(SignUpDTO dto) {
        
        List<String> erros = new ArrayList<>();

        if (dto.tipoCurso() == null) {
            erros.add("Tipo do curso é obrigatório para aluno");
        }

        if (dto.nomeUsuario() == null) {
            erros.add("Nome é obrigatório para aluno");
        }

        if (dto.email() == null) {
            erros.add("Email é obrigatório para aluno");
        }

        if (dto.nusp() == null) {
            erros.add("Número USP é obrigatório para aluno");
        }

        if (dto.rg() == null) {
            erros.add("RG é obrigatório para aluno");
        }

        if (dto.nomeOrientador() == null) {
            erros.add("Nome do orientador é obrigatório para aluno");
        }

        if (dto.localNascimento() == null) {
            erros.add("Local de nascimento é obrigatório para aluno");
        }

        if (dto.nacionalidade() == null) {
            erros.add("Nacionalidade é obrigatória para aluno");
        }

        if (dto.linkLattes() == null) {
            erros.add("Link do lattes é obrigatório para aluno");
        }

        if (dto.dataNascimento() == null) {
            erros.add("Data de nascimento é obrigatória para aluno");
        }

        if (dto.dataUltimaAttLattes() == null) {
            erros.add("Data de última atualização do lattes é obrigatória para aluno");
        }

        if (dto.dataMatricula() == null) {
            erros.add("Data de matrícula é obrigatória para aluno");
        }

        if (dto.disciplinasAprovadas() == null) {
            erros.add("Lista de disciplinas aprovadas é obrigatória para aluno");
        }

        if (dto.disciplinasReprovadas() == null) {
            erros.add("Lista de disciplinas reprovadas é obrigatória para aluno");
        }

        return erros;
    }

    public static List<String> validateTeacherData(SignUpDTO dto) {
        List<String> erros = new ArrayList<>();

        if (dto.nomeUsuario() == null) {
            erros.add("Nome é obrigatório para orientador");
        }

        if (dto.email() == null) {
            erros.add("Email é obrigatório para orientador");
        }

        return erros;
    }

    public static List<String> validateCcpData(SignUpDTO dto) {
        List<String> erros = new ArrayList<>();

        if (dto.nomeUsuario() == null) {
            erros.add("Nome é obrigatório para CCP");
        }

        if (dto.email() == null) {
            erros.add("Email é obrigatório para CCP");
        }

        return erros;
    }
}