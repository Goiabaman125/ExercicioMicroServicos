package com.aula.pos.mspedido.dto;

import java.io.Serializable;
import java.util.List;

public class PagamentoRequestDto implements Serializable {
    private Long pedidoId;
    private Double valorTotal;
    private List<Long> produtoIds;
    private List<Integer> quantidades;
    private String cliente;

    public PagamentoRequestDto() {}

    public PagamentoRequestDto(Long pedidoId, Double valorTotal, List<Long> produtoIds, List<Integer> quantidades, String cliente) {
        this.pedidoId = pedidoId;
        this.valorTotal = valorTotal;
        this.produtoIds = produtoIds;
        this.quantidades = quantidades;
        this.cliente = cliente;
    }

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
    public List<Long> getProdutoIds() { return produtoIds; }
    public void setProdutoIds(List<Long> produtoIds) { this.produtoIds = produtoIds; }
    public List<Integer> getQuantidades() { return quantidades; }
    public void setQuantidades(List<Integer> quantidades) { this.quantidades = quantidades; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
}
