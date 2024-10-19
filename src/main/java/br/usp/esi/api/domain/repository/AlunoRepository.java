package br.usp.esi.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usp.esi.api.domain.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}