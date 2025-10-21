package com.aula.pos.msproduto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProdutoRequestDto(
        @NotBlank String nome,
        @PositiveOrZero int quantidade,
        String descricao,
        @Positive float preco) {
}