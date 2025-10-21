package com.aula.pos.mspedido.controller;

import com.aula.pos.mspedido.dto.PedidoDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.aula.pos.mspedido.service.PedidoService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final WebServerApplicationContext context;

    @GetMapping("/port")
    public String getPort() {
        return String.valueOf(context.getWebServer().getPort());
    }

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> findAll(
            @PageableDefault(size = 5) Pageable pagination) {
        return ResponseEntity.ok(pedidoService.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pedidoService.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PedidoDto> save(@Valid @RequestBody PedidoDto dto) {
        var saved = pedidoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> update(
            @PathVariable Long id,
            @Valid @RequestBody PedidoDto dto) {
        var updated = pedidoService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}