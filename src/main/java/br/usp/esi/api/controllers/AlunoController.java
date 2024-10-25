package br.usp.esi.api.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.usp.esi.api.domain.dto.AlunoDetalhamentoDTO;
import br.usp.esi.api.domain.dto.ListRelatoriosAlunoDTO;
import br.usp.esi.api.domain.model.Aluno;
import br.usp.esi.api.domain.model.AlunoDisciplinasAprovadas;
import br.usp.esi.api.domain.model.AlunoDisciplinasReprovadas;
import br.usp.esi.api.domain.model.Relatorio;
import br.usp.esi.api.domain.model.User;
import br.usp.esi.api.domain.repository.AlunoDisciplinasAprovadasRepository;
import br.usp.esi.api.domain.repository.AlunoDisciplinasReprovadasRepository;
import br.usp.esi.api.domain.repository.AlunoRepository;
import br.usp.esi.api.domain.repository.RelatorioRepository;
import br.usp.esi.api.infra.exception.NoReportsForStudentException;
import br.usp.esi.api.infra.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/aluno")
@SecurityRequirement(name = "bearer-key")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoDisciplinasAprovadasRepository disciplinasAprovadasRepository;

    @Autowired
    private AlunoDisciplinasReprovadasRepository disciplinasReprovadasRepository;

    @Autowired
    private RelatorioRepository relatorioRepository;

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

    @GetMapping("/meusRelatorios")
    public ResponseEntity<List<ListRelatoriosAlunoDTO>> listarMeusRelatorios() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Aluno alunoLogado = alunoRepository.pegarAlunoPorIdUsuario(user.getId());

        List<Relatorio> relatorios = relatorioRepository.pegaRelatoriosDoAluno(alunoLogado.getId());
        if (relatorios.size() == 0) {
            throw new NoReportsForStudentException("O aluno não possui relatórios associados!");
        }

        List<ListRelatoriosAlunoDTO> listaDeAlunos = new ArrayList<>();

        for (Relatorio relatorio: relatorios) listaDeAlunos.add(new ListRelatoriosAlunoDTO(relatorio));
        return ResponseEntity.ok(listaDeAlunos);
    }
}