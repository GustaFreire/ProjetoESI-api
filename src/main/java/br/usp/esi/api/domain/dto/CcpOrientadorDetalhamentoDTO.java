package br.usp.esi.api.domain.dto;

import br.usp.esi.api.domain.model.Ccp;
import br.usp.esi.api.domain.model.Orientador;

public record CcpOrientadorDetalhamentoDTO(Long roleId, Long userId, String nome, String email) {

    public CcpOrientadorDetalhamentoDTO(Ccp ccp) {
        this(ccp.getId(), ccp.getUser().getId(), ccp.getNome(), ccp.getEmail());
    }

    public CcpOrientadorDetalhamentoDTO(Orientador orientador) {
        this(orientador.getId(), orientador.getUser().getId(), orientador.getNome(), orientador.getEmail());
    }
}