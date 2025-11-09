package com.aula.pos.mspagamento.dto;

import com.aula.pos.mspagamento.enums.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoResponseDto(
        Long id,
        String codigo,
        StatusPagamento status,
        LocalDateTime expiracao,
        BigDecimal valor,
        Long pedidoId
) {}
