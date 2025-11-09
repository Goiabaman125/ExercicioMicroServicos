package com.aula.pos.mspagamento.controller;

import com.aula.pos.mspagamento.dto.PagamentoRequestDto;
import com.aula.pos.mspagamento.dto.PagamentoResponseDto;
import com.aula.pos.mspagamento.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService service;

    /**
     * Registra um novo pagamento (chamado internamente ou via POST)
     */
    @PostMapping
    public ResponseEntity<PagamentoResponseDto> registrar(@Valid @RequestBody PagamentoRequestDto dto) {
        PagamentoResponseDto resp = service.registrarPagamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    /**
     * Lista todos os pagamentos
     */
    @GetMapping
    public ResponseEntity<List<PagamentoResponseDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    /**
     * Busca pagamento por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDto> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    /**
     * Confirma um pagamento (usado pelo ms-pedidos)
     */
    @PutMapping("/{codigo}/confirmar")
    public ResponseEntity<Void> confirmar(@PathVariable String codigo) {
        service.confirmar(codigo);
        return ResponseEntity.noContent().build();
    }
}