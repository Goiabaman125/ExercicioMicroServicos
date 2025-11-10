-- MySQL / MariaDB
CREATE TABLE tb_pedidos (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            data_pedido DATETIME NOT NULL,
                            valor_total DOUBLE,
                            id_produtos VARCHAR(255) NOT NULL,
                            quantidade_produtos VARCHAR(255) NOT NULL
);


CREATE TABLE pedido_produtos (
    pedido_id  BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    PRIMARY KEY (pedido_id, produto_id),
    CONSTRAINT fk_pedido
        FOREIGN KEY (pedido_id) REFERENCES tb_pedidos(id)
        ON DELETE CASCADE
);