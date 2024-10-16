package br.usp.esi.api.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.usp.esi.api.domain.dto.ExceptionDto;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistisException(UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(new ExceptionDto(ex.getMessage()));
    }
}