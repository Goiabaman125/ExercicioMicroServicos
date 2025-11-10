package com.aula.pos.mspedido.client;

import com.aula.pos.mspedido.dto.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name = "msprodutos", url = "http://localhost:8081")
public interface ProdutoClient {
    @PostMapping("/produtos/ids")
    List<ProdutoDto> buscarProdutosPorIds(@RequestBody List<Long> ids);
}
