package br.usp.esi.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(

    @NotBlank(message = "Email de usuário é obrigatório")
    String username, 
    
    @NotBlank(message = "Senha é obrigatória")
    String password) {
    
}