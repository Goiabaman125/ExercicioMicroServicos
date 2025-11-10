package com.aula.pos.mspagamento.service;

import com.aula.pos.mspagamento.cliente.PedidoCliente;
import com.aula.pos.mspagamento.dto.PagamentoRequestDto;
import com.aula.pos.mspagamento.dto.PagamentoResponseDto;
import com.aula.pos.mspagamento.enums.StatusPagamento;
import com.aula.pos.mspagamento.model.Pagamento;
import com.aula.pos.mspagamento.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository repository;
    private final PedidoCliente pedidoCliente;

    /* ---------- CRUD ---------- */

    public PagamentoResponseDto buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado"));
    }

    /* ---------- Ações de negócio ---------- */

    @Transactional
    @Override
    public PagamentoResponseDto registrarPagamento(PagamentoRequestDto dto) {
        Pagamento p = Pagamento.builder()
                .valor(BigDecimal.valueOf(dto.getValorTotal()))
                .pedidoId(dto.getPedidoId())
                .codigo(UUID.randomUUID().toString())
                .expiracao(LocalDateTime.now().plusHours(24))
                .status(StatusPagamento.CRIADO)
                .build();
        Pagamento saved = repository.save(p);
        return toDto(saved);
    }

    @Transactional
    @Override
    public void processarPagamentoAsync(PagamentoRequestDto dto) {
        Pagamento p = Pagamento.builder()
                .valor(BigDecimal.valueOf(dto.getValorTotal()))
                .pedidoId(dto.getPedidoId())
                .codigo(UUID.randomUUID().toString())
                .expiracao(LocalDateTime.now().plusHours(24))
                .status(StatusPagamento.CONFIRMADO)   // regra do exercício
                .build();
        repository.save(p);
        pedidoCliente.notificarPagamentoConfirmado(dto.getPedidoId(), p.getCodigo());
    }

    @Transactional
    public void confirmar(String codigo) {
        Pagamento pag = repository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado"));
        pag.setStatus(StatusPagamento.CONFIRMADO);
        repository.save(pag);
    }

    @Override
    public List<PagamentoResponseDto> listar() {
        return List.of();
    }

    /* ---------- Conversor ---------- */
    public PagamentoResponseDto toDto(Pagamento entity) {
        return new PagamentoResponseDto(
                entity.getId(),
                entity.getCodigo(),
                entity.getStatus(),
                entity.getExpiracao(),
                entity.getValor(),
                entity.getPedidoId()
        );
    }
}