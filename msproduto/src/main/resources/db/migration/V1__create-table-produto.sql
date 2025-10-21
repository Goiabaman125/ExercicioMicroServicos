-- V1__create-table-produto.sql  (MySQL/MariaDB)
CREATE TABLE tb_produtos (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(150) NOT NULL,
    quantidade  INT          NOT NULL,
    descricao   TEXT,
    preco       FLOAT        NOT NULL
);