package com.aula.pos.mspedido.service;

import com.aula.pos.mspedido.dto.PedidoDto;
import com.aula.pos.mspedido.model.Pedido;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aula.pos.mspedido.repository.PedidoRepository;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Override
    public Page<PedidoDto> findAll(Pageable pagination) {
        return pedidoRepository.findAll(pagination).map(PedidoDto::new);
    }

    @Override
    public PedidoDto findById(Long id) {
        return pedidoRepository.findById(id)
                .map(PedidoDto::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public PedidoDto save(PedidoDto pedidoDto) {
        var pedido = Pedido.fromDto(pedidoDto);
        return new PedidoDto(pedidoRepository.save(pedido));
    }

    @Transactional
    @Override
    public PedidoDto update(Long id, PedidoDto pedidoDto) {
        var pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        pedido.setDataPedido(pedidoDto.dataPedido());
        pedido.setIdProdutos(pedidoDto.idProdutos());

        return new PedidoDto(pedidoRepository.save(pedido));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }
}