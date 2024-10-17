package br.usp.esi.api.domain.dto;

import br.usp.esi.api.domain.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(

    @NotBlank @Email
    String username,

    @NotBlank
    String password,

    @NotNull
    UserRole role) {
    
}