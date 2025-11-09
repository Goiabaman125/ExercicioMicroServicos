package com.aula.pos.mspagamento.dto;

public record PagamentoConfirmadoDto(
        Long pedidoId,
        String codigoPagamento,
        boolean confirmado
) {}
