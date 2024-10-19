package br.usp.esi.api.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.usp.esi.api.domain.dto.AlunoDetalhamentoDTO;
import br.usp.esi.api.domain.model.AlunoDisciplinasAprovadas;
import br.usp.esi.api.domain.model.AlunoDisciplinasReprovadas;
import br.usp.esi.api.domain.repository.AlunoDisciplinasAprovadasRepository;
import br.usp.esi.api.domain.repository.AlunoDisciplinasReprovadasRepository;
import br.usp.esi.api.domain.repository.AlunoRepository;
import br.usp.esi.api.infra.exception.UserNotFoundException;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoDisciplinasAprovadasRepository disciplinasAprovadasRepository;

    @Autowired
    private AlunoDisciplinasReprovadasRepository disciplinasReprovadasRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var aluno = alunoRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Aluno de ID " + id + " não encontrado"));

        var aprovadas = disciplinasAprovadasRepository.findAllByAluno(aluno);
        var reprovadas = disciplinasReprovadasRepository.findAllByAluno(aluno);

        List<String> disciplinasAprovadas = new ArrayList<>();
        List<String> disciplinasReprovadas = new ArrayList<>();
        
        for (AlunoDisciplinasAprovadas disciplina: aprovadas) {
            disciplinasAprovadas.add(disciplina.getDisciplina());
        }
        for (AlunoDisciplinasReprovadas disciplina: reprovadas) {
            disciplinasReprovadas.add(disciplina.getDisciplina());
        }

        return ResponseEntity.ok(new AlunoDetalhamentoDTO(aluno, disciplinasAprovadas, disciplinasReprovadas));
    }
}