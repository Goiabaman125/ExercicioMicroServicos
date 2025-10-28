# Exercicio MicroServicos
Criar um sistema com as boas práticas de microsserviços

Grupo:  
- Artur Freitas Palmeira — RGM 42900590 - artur.palmeira@cs.unipe.edu.br 
- Geraldo Camilo F. T. Valencia — RGM 44194153  - geraldovalencia@gmail.com
- Robson Lima Palmeira — RGM 44214341 - robson.palmeira@cs.unipe.edu.br

<img width="578" height="709" alt="image" src="https://github.com/user-attachments/assets/86afbce1-d495-4825-b61d-8c0c982418ad" />


## Objetivo
Implementar um ecossistema de microsserviços em Java com Spring, seguindo boas práticas, para gerenciar Produtos e Pedidos, conforme o diagrama fornecido (Produto, Pedido e enum StatusPedido: CRIADO, CONFIRMADO, CANCELADO). 

## Arquitetura
- product-service: CRUD completo de Produto (id, nome, quantidade, descricao, preco). 
- pedido-service: CRUD de Pedido (id, dataPedido, idProdutos) + status do pedido com enum StatusPedido. 
- service-registry: Registro e descoberta de serviços (Spring Cloud Netflix Eureka). 
- api-gateway: Roteamento de requisições externas para os serviços internos (Spring Cloud Gateway). 

## Padrões e boas práticas
- Camadas por serviço: model, repository, dto, service e controller. 
- Comunicação síncrona via HTTP através do API Gateway. 
- Configurações externas por ambiente via application.yml. 


