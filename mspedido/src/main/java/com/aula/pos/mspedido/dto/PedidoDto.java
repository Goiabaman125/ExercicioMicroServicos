package com.aula.pos.mspedido.dto;

import com.aula.pos.mspedido.model.Pedido;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDto(
        Long id,                       // opcional na criação
        @NotNull LocalDateTime dataPedido,
        @NotNull List<Long> idProdutos
) {
    /* Construtor que copia os dados da entidade */
    public PedidoDto(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getIdProdutos()
        );
    }
}