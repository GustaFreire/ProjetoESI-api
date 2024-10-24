package br.usp.esi.api.domain.dto;

import br.usp.esi.api.domain.model.Ccp;
import br.usp.esi.api.domain.model.Orientador;

public record OrientadorCcpDetalhamentoDTO(Long id, Long idUser,String nomeUsuario, String email) {

    public OrientadorCcpDetalhamentoDTO(Orientador orientador) {
        this(orientador.getId(), orientador.getUser().getId(), orientador.getNome(), orientador.getEmail());
    }

    public OrientadorCcpDetalhamentoDTO(Ccp ccp) {
        this(ccp.getId(), ccp.getUser().getId(), ccp.getNome(), ccp.getEmail());
    }
}