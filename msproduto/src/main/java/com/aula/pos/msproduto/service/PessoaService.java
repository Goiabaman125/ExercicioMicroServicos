package com.aula.pos.msproduto.service;

import com.aula.pos.msproduto.dto.PessoaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaService {

    Page<PessoaDto> findAll(Pageable pagination);
    PessoaDto findById(Long id);
    PessoaDto save(PessoaDto pessoaDto);
    PessoaDto update(Long id, PessoaDto pessoaDto);
    void delete(Long id);
}
