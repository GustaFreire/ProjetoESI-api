package br.usp.esi.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record SigInDTO(

    @NotBlank(message = "Username é obrigatório")
    String username, 
    
    @NotBlank(message = "Senha é obrigatória")
    String password) {
}