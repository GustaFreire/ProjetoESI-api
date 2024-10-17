package br.usp.esi.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(

    @NotBlank
    String username, 
    
    @NotBlank
    String password) {
    
}