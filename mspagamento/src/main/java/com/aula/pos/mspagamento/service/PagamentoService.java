package com.aula.pos.mspagamento.service;

import com.aula.pos.mspagamento.dto.PagamentoRequestDto;
import com.aula.pos.mspagamento.dto.PagamentoResponseDto;

import java.util.List;

public interface PagamentoService {
    PagamentoResponseDto registrarPagamento(PagamentoRequestDto dto);
    void processarPagamentoAsync(PagamentoRequestDto dto);

    PagamentoResponseDto buscarPorId(Long id);

    void confirmar(String codigo);

    List<PagamentoResponseDto> listar();
}