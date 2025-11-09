package com.aula.pos.mspagamento.cliente;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-pedidos")
public interface PedidoCliente {

    /**
     * Confirma o pagamento para o ms-pedidos
     */
    @PutMapping("/api/pedidos/{pedidoId}/pagamento-confirmado")
    void notificarPagamentoConfirmado(@PathVariable("pedidoId") Long pedidoId,
                                      @RequestParam("codigoPagamento") String codigoPagamento);
}
