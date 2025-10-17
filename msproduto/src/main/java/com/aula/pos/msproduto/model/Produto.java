package com.aula.pos.msproduto.model;

import com.aula.pos.msproduto.dto.PessoaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150)
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    public static Produto fromDto(PessoaDto pessoaDto){
        return new Produto(pessoaDto.id(), pessoaDto.nome(), pessoaDto.email(), pessoaDto.cpf(), pessoaDto.telefone());
    }

}
