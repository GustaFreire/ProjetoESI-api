package br.usp.esi.api.infra.exception;

public class NoReportsForStudentException extends RuntimeException {

    public NoReportsForStudentException(String message) {
        super(message);
    }
}