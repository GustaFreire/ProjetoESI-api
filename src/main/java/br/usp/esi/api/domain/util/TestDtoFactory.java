package br.usp.esi.api.domain.util;

import java.time.LocalDate;
import java.util.Arrays;

import br.usp.esi.api.domain.dto.RetornoDTO;
import br.usp.esi.api.domain.dto.SigInDTO;
import br.usp.esi.api.domain.dto.SignUpDTO;
import br.usp.esi.api.domain.dto.TokenDTO;
import br.usp.esi.api.domain.enums.TipoCurso;
import br.usp.esi.api.domain.enums.UserRole;

public class TestDtoFactory {

    public static SignUpDTO criaDtoCadastroAluno() {
        return new SignUpDTO("joao.silva@example.com",
        "senhaSegura123",
        UserRole.DISCENTE, 
        TipoCurso.MESTRADO, 
        "João da Silva Torres", 
        "joao.silva@example.com", 
        "11839927", 
        "12.345.678-9", 
        "Dr. Carlos Pereira", 
        "São Paulo, SP", 
        "Brasileiro", 
        "http://lattes.cnpq.br/1234567890123456", 
        LocalDate.of(1995, 5, 15), 
        LocalDate.of(2023, 10, 1),
        LocalDate.of(2022, 2, 10), 
        LocalDate.of(2023, 6, 15), 
        LocalDate.of(2023, 8, 15), 
        Arrays.asList("AED 2", "ESI", "Calculo I"), 
        Arrays.asList("MVGA", "Calculo II", "MD"));
    }

    public static RetornoDTO criaDtoResponseCadastroAluno() {
        return new RetornoDTO("Usuario cadastrado com sucesso!");
    }

    public static SigInDTO criaDtoRequestLogin() {
        return new SigInDTO("joao.silva@example.com", "senhaSegura123");
    }

    public static TokenDTO criaDtoResponseLogin() {
        return new TokenDTO(null);
    }
}