package br.usp.esi.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.usp.esi.api.domain.model.Aluno;
import br.usp.esi.api.domain.model.AlunoDisciplinasAprovadas;

public interface AlunoDisciplinasAprovadasRepository extends JpaRepository<AlunoDisciplinasAprovadas, Long> {

    List<AlunoDisciplinasAprovadas> findAllByAluno(Aluno aluno);

}