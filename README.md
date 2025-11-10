# Exercicio MicroServicos

Criar um sistema com as boas práticas de microsserviços

Grupo:
- Artur Freitas Palmeira — RGM 42900590 - [artur.palmeira@cs.unipe.edu.br](mailto:artur.palmeira@cs.unipe.edu.br)
- Geraldo Camilo F. T. Valencia — RGM 44194153 - [geraldovalencia@gmail.com](mailto:geraldovalencia@gmail.com)
- Robson Lima Palmeira — RGM 44214341 - [robson.palmeira@cs.unipe.edu.br](mailto:robson.palmeira@cs.unipe.edu.br)

## Objetivo

Implementar um ecossistema de microsserviços em Java com Spring, seguindo boas práticas, para gerenciar Produtos e Pedidos, conforme o diagrama fornecido (Produto, Pedido e enum StatusPedido: CRIADO, CONFIRMADO, CANCELADO).

## Arquitetura

- **product-service**: CRUD completo de Produto (id, nome, quantidade, descricao, preco).
- **pedido-service**: CRUD de Pedido (id, dataPedido, idProdutos) + status do pedido com enum StatusPedido.
- **service-registry**: Registro e descoberta de serviços (Spring Cloud Netflix Eureka).
- **api-gateway**: Roteamento de requisições externas para os serviços internos (Spring Cloud Gateway).

## Padrões e boas práticas

- Camadas por serviço: model, repository, dto, service e controller.
- Comunicação síncrona via HTTP através do API Gateway.
- Configurações externas por ambiente via `application.yml`.

## Sequência recomendada para subir os serviços

1. service-registry
2. api-gateway
3. msprodutos
4. mspedidos
5. mspagamento

## Exemplos de URLs para testar APIs

| Serviço        | Porta | Exemplo de endpoint                      |
| -------------- | ----- | ---------------------------------------- |
| msprodutos     | 8081  | http://localhost:8081/produtos           |
| mspedidos      | 8082  | http://localhost:8082/pedidos            |
| mspagamento    | 8083  | http://localhost:8083/pagamentos         |

## Exemplos de requisições para POST (sem imagens, apenas JSON/texto)

### Cadastro de Produto

POST http://localhost:8081/produtos

{
  "nome": "Notebook",
  "descricao": "Ultra Fino",
  "preco": 3999.99,
  "quantidade": 10
}


### Cadastro de Pedido

POST http://localhost:8082/pedidos

{
  "idProdutos": [1],
  "quantidadeProdutos": [1],
  "cliente": "Geraldo A.",
  "dataPedido": "2025-11-09T22:10:00",
  "status": "CRIADO"
}



### Cadastro de Pagamento

POST http://localhost:8083/api/pagamentos


{
    "pedidoId": 1,
    "valorTotal": 99.99,
    "metodo": "CARTAO"
}


---

A sequência correta de testes é: produtos → pedidos → pagamentos.
