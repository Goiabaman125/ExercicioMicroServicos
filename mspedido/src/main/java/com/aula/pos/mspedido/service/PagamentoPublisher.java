package com.aula.pos.mspedido.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import com.aula.pos.mspedido.config.RabbitConfig;
import com.aula.pos.mspedido.dto.PagamentoRequestDto;

@Service
public class PagamentoPublisher {
    private final RabbitTemplate template;

    public PagamentoPublisher(RabbitTemplate template) {
        this.template = template;
    }

    public void enviarParaPagamento(PagamentoRequestDto dto) {
        template.convertAndSend(RabbitConfig.QUEUE, dto);
    }
}
