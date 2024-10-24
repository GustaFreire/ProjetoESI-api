package br.usp.esi.api.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RelatorioCcpDTO(
    
    @NotBlank(message = "O relatorio precisa de um nome")
    String nome, 
    
    @NotNull(message = "O relatorio precisa de uma data limite")
    @Future(message = "Data limite de envio de relatório precisa ser maior que a data atual")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataLimite) {

}