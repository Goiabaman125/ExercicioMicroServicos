package com.aula.pos.msproduto.service;

import com.aula.pos.msproduto.dto.ProdutoDto;
import com.aula.pos.msproduto.dto.ProdutoRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoService {

    Page<ProdutoDto> findAll(Pageable p);
    ProdutoDto findById(Long id);
    ProdutoDto save(ProdutoRequestDto dto);
    ProdutoDto update(Long id, ProdutoRequestDto dto);
    void delete(Long id);
    List<ProdutoDto> findByIds(List<Long> ids);

}