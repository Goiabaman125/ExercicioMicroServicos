package com.aula.pos.mspagamento.dto;

import java.io.Serializable;

public class PagamentoRequestDto implements Serializable {
    private Long pedidoId;
    private Double valorTotal;

    public PagamentoRequestDto() {}

    public PagamentoRequestDto(Long pedidoId, Double valorTotal) {
        this.pedidoId = pedidoId;
        this.valorTotal = valorTotal;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
