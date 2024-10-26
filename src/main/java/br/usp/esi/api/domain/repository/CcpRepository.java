package br.usp.esi.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.usp.esi.api.domain.model.Aluno;
import br.usp.esi.api.domain.model.Ccp;

public interface CcpRepository extends JpaRepository<Ccp, Long> {

    @Query("""
            SELECT c FROM Ccp c
            WHERE c.user.id = :idUsuario
            """)
    Ccp pegarCcpPorIdUsuario(Long idUsuario);
}