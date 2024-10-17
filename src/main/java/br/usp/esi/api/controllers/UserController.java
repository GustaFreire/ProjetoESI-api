package br.usp.esi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.usp.esi.api.domain.dto.UserCadastroDto;
import br.usp.esi.api.domain.dto.UserDetalhamentoDto;
import br.usp.esi.api.domain.model.User;
import br.usp.esi.api.domain.repository.UserRepository;
import br.usp.esi.api.infra.exception.UserAlreadyExistsException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UserCadastroDto dto, UriComponentsBuilder uriBuilder) {
        var passwordEncoded = encoder.encode(dto.password());
        var user = new User(dto, passwordEncoded);
        var userExistis = repository.findByUsername(user.getUsername()) != null;
        
        if (userExistis) {
            throw new UserAlreadyExistsException("Ja existe um usuario com o email informado");
        }

        repository.save(user);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDetalhamentoDto(user));
    }
}