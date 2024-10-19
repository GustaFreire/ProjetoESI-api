package br.usp.esi.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usp.esi.api.domain.model.Aluno;
import br.usp.esi.api.domain.model.AlunoDisciplinasReprovadas;

public interface AlunoDisciplinasReprovadasRepository extends JpaRepository<AlunoDisciplinasReprovadas, Long> {

    List<AlunoDisciplinasReprovadas> findAllByAluno(Aluno aluno);
}