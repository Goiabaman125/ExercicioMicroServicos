package com.aula.pos.msproduto.dto;

import com.aula.pos.msproduto.model.Produto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PessoaDto(Long id,
                       @NotBlank String nome,
                       @NotBlank @Email String email,
                       @NotBlank String cpf,
                       @NotBlank String telefone) {
    public PessoaDto(Produto p){
        this(p.getId(), p.getNome(), p.getEmail(), p.getCpf(), p.getTelefone());
    }
}
