package br.usp.esi.api.domain.dto;

import br.usp.esi.api.domain.enums.UserRole;
import br.usp.esi.api.domain.model.User;

public record UserDetalhamentoDto(Long id, String username, UserRole role) {

    public UserDetalhamentoDto(User user) {
        this(user.getId(), user.getUsername(), user.getRole());
    }
}