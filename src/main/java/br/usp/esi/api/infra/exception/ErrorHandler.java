package br.usp.esi.api.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.usp.esi.api.domain.dto.RetornoDTO;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistisException(UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(new RetornoDTO(ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErroEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RetornoDTO(ex.getMessage()));
    }

    @ExceptionHandler(NoReportsForStudentException.class)
    public ResponseEntity<?> tratarErroNoReportsForStudentException(NoReportsForStudentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RetornoDTO(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ErroDto::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> tratarErro400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new RetornoDTO("Erro: " + ex.getLocalizedMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> tratarErroBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RetornoDTO("Credenciais inválidas"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> trataErroStudentNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RetornoDTO(ex.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> tratarErroAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RetornoDTO("Falha na autenticação"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> tratarErroAcessoNegado() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new RetornoDTO("Acesso negado"));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<?> tratarErroAuthService() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RetornoDTO("Erro: Email não encontrado na base"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> tratarErro500(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RetornoDTO("Erro: " + ex.getLocalizedMessage()));
    }

    private record ErroDto(String campo, String mensagem) {
        public ErroDto(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}