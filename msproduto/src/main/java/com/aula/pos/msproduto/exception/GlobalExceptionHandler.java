package com.aula.pos.msproduto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseErrorDto("Recurso não encontrado", HttpStatus.NOT_FOUND.name()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDto> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseErrorDto("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR.name()));
    }

    /* DTO local (ou em arquivo separado) */
    public record ResponseErrorDto(String mensagem, String status) {}
}