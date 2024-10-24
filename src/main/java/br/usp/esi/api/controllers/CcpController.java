package br.usp.esi.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.usp.esi.api.domain.dto.OrientadorCcpDetalhamentoDTO;
import br.usp.esi.api.domain.dto.RelatorioCcpDTO;
import br.usp.esi.api.domain.dto.RetornoDTO;
import br.usp.esi.api.domain.model.Aluno;
import br.usp.esi.api.domain.model.Relatorio;
import br.usp.esi.api.domain.repository.AlunoRepository;
import br.usp.esi.api.domain.repository.CcpRepository;
import br.usp.esi.api.domain.repository.RelatorioRepository;
import br.usp.esi.api.infra.exception.UserNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ccp")
public class CcpController {

    @Autowired
    private CcpRepository ccpRepository;

    @Autowired
    private RelatorioRepository relatorioRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        var ccp = ccpRepository.findById(id).orElseThrow(() -> new UserNotFoundException("CCP de ID " + id + " não encontrado"));
        return ResponseEntity.ok(new OrientadorCcpDetalhamentoDTO(ccp));
    }

    @PostMapping("/novoRelatorio")
    @Transactional
    public ResponseEntity<?> criarRelatorio(@RequestBody @Valid RelatorioCcpDTO dto) {
        List<Aluno> alunos = alunoRepository.findAll();
        System.out.println(alunos);

        for (Aluno aluno: alunos) { //pra cada aluno do banco cria um relatorio especifico
            Relatorio relatorio = new Relatorio(dto.nome(), dto.dataLimite(), aluno);
            relatorioRepository.save(relatorio);
        }
        
        return ResponseEntity.ok().body(new RetornoDTO("Relatorio enviado com sucesso aos alunos!"));
    }
}