package br.usp.esi.api.domain.dto;

import br.usp.esi.api.domain.model.User;

public record UserDtoListagem(Long id, String username, Boolean ativo) {

    public UserDtoListagem(User user) {
        this(user.getId(), user.getUsername(), user.getAtivo());
    }
}