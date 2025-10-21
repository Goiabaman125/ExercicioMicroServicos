package com.aula.pos.msproduto.dto;

import com.aula.pos.msproduto.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProdutoDto(
        Long id,
        @NotBlank String nome,
        int quantidade,
        String descricao,
        @Positive float preco) {

    /* Construtor que recebe a entidade */
    public ProdutoDto(Produto p) {
        this(p.getId(), p.getNome(), p.getQuantidade(), p.getDescricao(), p.getPreco());
    }
}