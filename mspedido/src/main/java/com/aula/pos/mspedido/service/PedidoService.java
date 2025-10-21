package com.aula.pos.mspedido.service;

import com.aula.pos.mspedido.dto.PedidoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService {

    Page<PedidoDto> findAll(Pageable pagination);
    PedidoDto findById(Long id);
    PedidoDto save(PedidoDto pedidoDto);
    PedidoDto update(Long id, PedidoDto pedidoDto);
    void delete(Long id);
}