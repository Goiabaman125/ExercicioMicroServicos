package com.aula.pos.mspagamento.cliente;

import com.aula.pos.mspagamento.dto.PagamentoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "mpagamento", path = "/pagamentos")
public interface PagamentoResourceCliente {
    @GetMapping("/{id}")
    ResponseEntity<PagamentoResponseDto> findById(@PathVariable Long id);

    @PutMapping("/{id}/qtde")
    ResponseEntity<PagamentoResponseDto> updateQtdeEvento(@PathVariable Long id, @RequestBody Integer novaQtde);
}
