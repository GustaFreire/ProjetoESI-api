package br.usp.esi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.usp.esi.api.domain.dto.LoginDTO;
import br.usp.esi.api.domain.dto.RegisterDTO;
import br.usp.esi.api.domain.dto.TokenDTO;
import br.usp.esi.api.domain.enums.UserRole;
import br.usp.esi.api.domain.model.Aluno;
import br.usp.esi.api.domain.model.User;
import br.usp.esi.api.domain.repository.AlunoRepository;
import br.usp.esi.api.domain.repository.UserRepository;
import br.usp.esi.api.domain.service.TokenService;
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
        userRepository.save(user);

        if (user.getRole().equals(UserRole.DISCENTE)) {
            Aluno aluno = new Aluno(dto, user);
            alunoRepository.save(aluno);
        }

        return ResponseEntity.ok().build();
    }
}