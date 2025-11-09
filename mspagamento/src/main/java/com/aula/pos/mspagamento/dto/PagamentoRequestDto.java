package com.aula.pos.mspagamento.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record PagamentoRequestDto(
        @NotNull
        Long pedidoId,

        @NotNull @Positive
        BigDecimal valor
) {}
