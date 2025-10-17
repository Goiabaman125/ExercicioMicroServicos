package com.aula.pos.msproduto.controller;

import com.aula.pos.msproduto.dto.PessoaDto;
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
import com.aula.pos.msproduto.service.PessoaServiceImpl;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaServiceImpl pessoaService;
    private final WebServerApplicationContext context;

    @GetMapping("/port")
    public String getPort(){
        return String.valueOf(context.getWebServer().getPort());
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDto>> findAll(@PageableDefault(size = 5) Pageable pagination){
        return ResponseEntity.ok(pessoaService.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> findById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(pessoaService.findById(id));
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PessoaDto> save(@Valid @RequestBody PessoaDto pessoaDto){
        var pessoaDtoSaved = pessoaService.save(pessoaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaDtoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> update(@PathVariable("id") Long id, @Valid @RequestBody PessoaDto pessoaDto){
        var pessoaDtoUpdated = pessoaService.update(id, pessoaDto);
        return ResponseEntity.ok(pessoaDtoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
