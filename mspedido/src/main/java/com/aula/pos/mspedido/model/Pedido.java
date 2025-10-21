package com.aula.pos.mspedido.model;

import com.aula.pos.mspedido.dto.PedidoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataPedido;

    @ElementCollection
    @CollectionTable(name = "pedido_produtos", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "produto_id")
    private List<Long> idProdutos;

    public static Pedido fromDto(PedidoDto dto) {
        Pedido p = new Pedido();
        p.setDataPedido(dto.dataPedido());
        p.setIdProdutos(dto.idProdutos());
        return p;
    }
}