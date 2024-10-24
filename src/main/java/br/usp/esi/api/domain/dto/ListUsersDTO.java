package br.usp.esi.api.domain.dto;

import br.usp.esi.api.domain.model.User;

public record ListUsersDTO(Long id, String username, Boolean ativo) {

    public ListUsersDTO(User user) {
        this(user.getId(), user.getUsername(), user.getAtivo());
    }
}