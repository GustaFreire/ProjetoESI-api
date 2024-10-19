package br.usp.esi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.usp.esi.api.domain.dto.OrientadorCcpDetalhamentoDto;
import br.usp.esi.api.domain.repository.OrientadorRepository;
import br.usp.esi.api.infra.exception.UserNotFoundException;

@RestController
@RequestMapping("/orientador")
public class OrientadorController {

    @Autowired
    private OrientadorRepository orientadorRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var orientador = orientadorRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Orientador de ID " + id + " não encontrado"));
        return ResponseEntity.ok(new OrientadorCcpDetalhamentoDto(orientador));
    }
}