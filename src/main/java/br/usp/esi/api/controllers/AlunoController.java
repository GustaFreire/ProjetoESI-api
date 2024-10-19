package br.usp.esi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.usp.esi.api.domain.dto.AlunoDetalhamentoDTO;
import br.usp.esi.api.domain.repository.AlunoRepository;
import br.usp.esi.api.infra.exception.StudentNotFoundException;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var aluno = repository.findById(id).orElseThrow(() -> new StudentNotFoundException("Aluno de ID " + id + " não encontrado"));
        return ResponseEntity.ok(new AlunoDetalhamentoDTO(aluno));
    }
}