package br.usp.esi.api.domain.dto;

import br.usp.esi.api.domain.model.Ccp;
import br.usp.esi.api.domain.model.Orientador;

public record OrientadorCcpDetalhamentoDto(Long id, Long idUser,String nomeUsuario, String email) {

    public OrientadorCcpDetalhamentoDto(Orientador orientador) {
        this(orientador.getId(), orientador.getUser().getId(), orientador.getNome(), orientador.getEmail());
    }

    public OrientadorCcpDetalhamentoDto(Ccp ccp) {
        this(ccp.getId(), ccp.getUser().getId(), ccp.getNome(), ccp.getEmail());
    }
}