package br.usp.esi.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.usp.esi.api.domain.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("""
            SELECT a FROM Aluno a
            WHERE a.user.id = :idUsuario
            """)
    Aluno pegarAlunoPorIdUsuario(Long idUsuario);
}