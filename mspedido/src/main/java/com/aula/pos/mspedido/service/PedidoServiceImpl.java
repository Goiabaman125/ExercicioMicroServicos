package com.aula.pos.mspedido.service;

import com.aula.pos.mspedido.client.ProdutoClient;
import com.aula.pos.mspedido.dto.PedidoDto;
import com.aula.pos.mspedido.dto.ProdutoDto;
import com.aula.pos.mspedido.model.Pedido;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aula.pos.mspedido.repository.PedidoRepository;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoClient produtoClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Page<PedidoDto> findAll(Pageable pagination) {
        return pedidoRepository.findAll(pagination).map(PedidoDto::new);
    }

    @Override
    public PedidoDto findById(Long id) {
        return pedidoRepository.findById(id)
                .map(PedidoDto::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public PedidoDto save(PedidoDto pedidoDto) {
        List<Long> ids = pedidoDto.idProdutos();
        List<Integer> quantidades = pedidoDto.quantidadeProdutos();
        List<ProdutoDto> produtos = produtoClient.buscarProdutosPorIds(ids);

        for (int i = 0; i < ids.size(); i++) {
            ProdutoDto prod = produtos.get(i);
            if (prod.getQuantidade() < quantidades.get(i)) {
                throw new RuntimeException("Estoque insuficiente para produto " + prod.getNome());
            }
        }

        double total = 0.0;
        for (int i = 0; i < ids.size(); i++) {
            total += produtos.get(i).getValor() * quantidades.get(i);
        }
        PedidoDto dtoAtualizado = new PedidoDto(
                pedidoDto.id(),
                pedidoDto.dataPedido(),
                pedidoDto.idProdutos(),
                pedidoDto.quantidadeProdutos(),
                total
        );

        var pedido = Pedido.fromDto(dtoAtualizado);
        PedidoDto pedidoSalvo = new PedidoDto(pedidoRepository.save(pedido));

        // Envie para RabbitMQ após salvar o pedido
        rabbitTemplate.convertAndSend(
                "pagamento.exchange",
                "pagamento.routing.key",
                pedidoSalvo // Ou crie um PagamentoRequestDto se preferir
        );

        return pedidoSalvo;
    }

    @Transactional
    @Override
    public PedidoDto update(Long id, PedidoDto pedidoDto) {
        var pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        pedido.setDataPedido(pedidoDto.dataPedido());
        pedido.setIdProdutosList(pedidoDto.idProdutos());
        pedido.setQuantidadeProdutosList(pedidoDto.quantidadeProdutos());
        pedido.setValorTotal(pedidoDto.valorTotal());

        return new PedidoDto(pedidoRepository.save(pedido));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }
}
