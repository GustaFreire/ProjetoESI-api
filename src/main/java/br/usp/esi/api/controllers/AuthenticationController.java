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

import br.usp.esi.api.domain.dto.LoginDto;
import br.usp.esi.api.domain.dto.RegisterDto;
import br.usp.esi.api.domain.model.User;
import br.usp.esi.api.domain.repository.UserRepository;
import br.usp.esi.api.infra.exception.UserAlreadyExistsException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto dto) {
        var user = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        authenticationManager.authenticate(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto dto) {
        if (repository.findByUsername(dto.username()) != null) {
            throw new UserAlreadyExistsException("Ja existe um usuario com o email informado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User user = new User(dto.username(), encryptedPassword, dto.role());
        repository.save(user);
        return ResponseEntity.ok().build();
    }
}