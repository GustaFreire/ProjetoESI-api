package br.usp.esi.api.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.usp.esi.api.domain.dto.LoginDTO;
import br.usp.esi.api.domain.dto.RegisterDTO;
import br.usp.esi.api.domain.dto.TokenDTO;
import br.usp.esi.api.domain.dto.UserDtoListagem;
import br.usp.esi.api.domain.enums.UserRole;
import br.usp.esi.api.domain.model.Aluno;
import br.usp.esi.api.domain.model.AlunoDisciplinasAprovadas;
import br.usp.esi.api.domain.model.AlunoDisciplinasReprovadas;
import br.usp.esi.api.domain.model.Ccp;
import br.usp.esi.api.domain.model.Orientador;
import br.usp.esi.api.domain.model.User;
import br.usp.esi.api.domain.repository.AlunoDisciplinasAprovadasRepository;
import br.usp.esi.api.domain.repository.AlunoDisciplinasReprovadasRepository;
import br.usp.esi.api.domain.repository.AlunoRepository;
import br.usp.esi.api.domain.repository.CcpRepository;
import br.usp.esi.api.domain.repository.OrientadorRepository;
import br.usp.esi.api.domain.repository.UserRepository;
import br.usp.esi.api.domain.service.TokenService;
import br.usp.esi.api.domain.util.RegisterDataValidator;
import br.usp.esi.api.infra.exception.UserAlreadyExistsException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private OrientadorRepository orientadorRepository;

    @Autowired
    private CcpRepository ccpRepository;

    @Autowired
    private AlunoDisciplinasAprovadasRepository disciplinasAprovadasRepository;

    @Autowired
    private AlunoDisciplinasReprovadasRepository disciplinasReprovadasRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto) {
        var user = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = authenticationManager.authenticate(user);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO dto) {
        if (userRepository.findByUsername(dto.username()) != null) {
            throw new UserAlreadyExistsException("Ja existe um usuario com o email informado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User user = new User(dto.username(), encryptedPassword, dto.role());

        if (user.getRole().equals(UserRole.DISCENTE)) {
            List<String> erros = RegisterDataValidator.validateStudentData(dto);
            if (erros.size() > 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
            }
            
            userRepository.save(user);
            Aluno aluno = new Aluno(dto, user);
            alunoRepository.save(aluno);

            //adicionando as disciplinas aprovadas e reprovadas nas tabelas
            for (String disciplina: dto.disciplinasAprovadas()) {
                disciplinasAprovadasRepository.save(new AlunoDisciplinasAprovadas(aluno, disciplina));
            }
            for (String disciplina: dto.disciplinasReprovadas()) {
                disciplinasReprovadasRepository.save(new AlunoDisciplinasReprovadas(aluno, disciplina));
            }
            
        } else if (user.getRole().equals(UserRole.DOSCENTE)) {
            List<String> erros = RegisterDataValidator.validateTeacherData(dto);

            if (erros.size() > 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
            }

            userRepository.save(user);
            Orientador orientador = new Orientador(dto, user);
            orientadorRepository.save(orientador);

        } else if (user.getRole().equals(UserRole.CCP)) {
            List<String> erros = RegisterDataValidator.validateCcpData(dto);

            if (erros.size() > 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
            }

            userRepository.save(user);
            Ccp ccp = new Ccp(dto, user);
            ccpRepository.save(ccp);

        } else if (user.getRole().equals(UserRole.ADMIN)) {
            userRepository.save(user);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserDtoListagem>> listar(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        var page = userRepository.findAll(pageable).map(UserDtoListagem::new);
        return ResponseEntity.ok(page);
    }
}