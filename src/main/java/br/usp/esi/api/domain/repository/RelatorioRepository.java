package br.usp.esi.api.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.usp.esi.api.domain.model.Relatorio;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {

    @Query("""
            SELECT r FROM Relatorio r
            WHERE r.aluno.id = :alunoId
            """)
    List<Relatorio> pegaRelatoriosDoAluno(Long alunoId);
}