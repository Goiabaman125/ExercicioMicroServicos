package com.aula.pos.mspagamento.amqp;

import com.aula.pos.mspagamento.dto.PagamentoRequestDto;
import com.aula.pos.mspagamento.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PagamentoConsumo {

    private final PagamentoService service;

    /**
     * Escuta a fila "pagamento.queue" e processa o pagamento
     * Publicada pelo ms-pedidos
     */
    @RabbitListener(queues = "pagamento.queue")
    public void consumir(PagamentoRequestDto dto) {
        log.info("► Pagamento recebido: {}", dto);
        service.processarPagamentoAsync(dto);
        log.info("► Pagamento processado com sucesso");
    }
}