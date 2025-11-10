package com.aula.pos.mspagamento.service;

import com.aula.pos.mspagamento.dto.PagamentoRequestDto;
import com.aula.pos.mspagamento.enums.StatusPagamento;
import com.aula.pos.mspagamento.model.Pagamento;
import com.aula.pos.mspagamento.repository.PagamentoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PagamentoConsumer {
    private final PagamentoRepository pagamentoRepository;

    public PagamentoConsumer(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @RabbitListener(queues = "pagamento.queue")
    public void receberPagamento(PagamentoRequestDto dto) {
        System.out.println("Recebido pagamento do pedido: " + dto.getPedidoId());

        Pagamento pagamento = Pagamento.builder()
                .valor(BigDecimal.valueOf(dto.getValorTotal())) // transforma de Double para BigDecimal
                .codigo(UUID.randomUUID().toString())
                .expiracao(LocalDateTime.now().plusMinutes(30))
                .pedidoId(dto.getPedidoId())
                .status(StatusPagamento.CRIADO)
                .build();

        pagamentoRepository.save(pagamento);

        System.out.println("Pagamento salvo no banco para pedido: " + dto.getPedidoId());
    }
}
