-- MySQL / MariaDB
CREATE TABLE tb_pedidos (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_pedido   DATETIME(6) NOT NULL,
    status        VARCHAR(20) NOT NULL
);

CREATE TABLE pedido_produtos (
    pedido_id  BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    PRIMARY KEY (pedido_id, produto_id),
    CONSTRAINT fk_pedido
        FOREIGN KEY (pedido_id) REFERENCES tb_pedidos(id)
        ON DELETE CASCADE
);