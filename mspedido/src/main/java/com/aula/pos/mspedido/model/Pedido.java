package com.aula.pos.mspedido.model;

import com.aula.pos.mspedido.dto.PedidoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

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

    @Column(name = "id_produtos", nullable = false)
    private String idProdutos;

    @Column(name = "quantidade_produtos", nullable = false)
    private String quantidadeProdutos;

    private Double valorTotal;

    // Conversão de List para String ("1,2,3")
    public void setIdProdutosList(List<Long> ids) {
        this.idProdutos = ids.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }
    public List<Long> getIdProdutosList() {
        return Arrays.stream(idProdutos.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    // Conversão de List para String ("3,5,2")
    public void setQuantidadeProdutosList(List<Integer> quantidades) {
        this.quantidadeProdutos = quantidades.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }
    public List<Integer> getQuantidadeProdutosList() {
        return Arrays.stream(quantidadeProdutos.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static Pedido fromDto(PedidoDto dto) {
        Pedido p = new Pedido();
        p.setDataPedido(dto.dataPedido());
        p.setValorTotal(dto.valorTotal());
        p.setIdProdutosList(dto.idProdutos());
        p.setQuantidadeProdutosList(dto.quantidadeProdutos());
        return p;
    }
}
