package com.aula.pos.mspedido.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutoDto {
    private Long id;
    private String nome;
    private Integer quantidade;
    @JsonProperty("preco")
    private Double valor;

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
}
