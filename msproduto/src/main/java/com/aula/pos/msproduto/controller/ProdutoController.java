package com.aula.pos.msproduto.controller;

import com.aula.pos.msproduto.dto.ProdutoDto;
import com.aula.pos.msproduto.dto.ProdutoRequestDto;
import com.aula.pos.msproduto.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;   // interface, não a impl diretamente

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> listar(@PageableDefault(size = 5) Pageable p) {
        return ResponseEntity.ok(service.findAll(p));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> criar(@Valid @RequestBody ProdutoRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id,
                                                @Valid @RequestBody ProdutoRequestDto request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/ids")
    public ResponseEntity<List<ProdutoDto>> buscarProdutosPorIds(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(service.findByIds(ids));
    }
}