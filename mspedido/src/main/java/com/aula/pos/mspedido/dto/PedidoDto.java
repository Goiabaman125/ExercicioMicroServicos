package com.aula.pos.mspedido.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDto(
        Long id,
        @NotNull LocalDateTime dataPedido,
        @NotNull List<Long> idProdutos,
        @NotNull List<Integer> quantidadeProdutos,
        Double valorTotal
) implements Serializable {
    public PedidoDto(com.aula.pos.mspedido.model.Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getIdProdutosList(),
                pedido.getQuantidadeProdutosList(),
                pedido.getValorTotal()
        );
    }

    public PedidoDto withValorTotal(Double novoValorTotal) {
        return new PedidoDto(
                this.id,
                this.dataPedido,
                this.idProdutos,
                this.quantidadeProdutos,
                novoValorTotal
        );
    }
}
