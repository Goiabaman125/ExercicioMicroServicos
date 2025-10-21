package com.aula.pos.msproduto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;

    private int quantidade;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private float preco;

    /*  ---- opcional: só descomente depois que ProdutoDto existir ----
    public static Produto fromDto(ProdutoDto dto) {
        return new Produto(
                dto.id(),
                dto.nome(),
                dto.quantidade(),
                dto.descricao(),
                dto.preco()
        );
    }
    */
}