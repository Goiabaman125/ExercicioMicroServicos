package com.aula.pos.mspagamento.exception;

import com.aula.pos.mspagamento.dto.ErroDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroDto> handle(Exception e) {
        return ResponseEntity.badRequest()
                .body(new ErroDto("erro", e.getMessage()));
    }
}