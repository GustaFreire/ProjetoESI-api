package br.usp.esi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.usp.esi.api.domain.dto.OrientadorCcpDetalhamentoDto;
import br.usp.esi.api.domain.repository.CcpRepository;
import br.usp.esi.api.infra.exception.UserNotFoundException;

@RestController
@RequestMapping("/ccp")
public class CcpController {

    @Autowired
    private CcpRepository ccpRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var ccp = ccpRepository.findById(id).orElseThrow(() -> new UserNotFoundException("CCP de ID " + id + " não encontrado"));
        return ResponseEntity.ok(new OrientadorCcpDetalhamentoDto(ccp));
    }
}