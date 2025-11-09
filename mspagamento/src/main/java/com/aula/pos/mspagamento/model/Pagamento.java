package com.aula.pos.mspagamento.model;

import com.aula.pos.mspagamento.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "tb_pagamentos")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;
    private String codigo;          // UUID aleatório
    private LocalDateTime expiracao;
    private Long pedidoId;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status; // CRIADO, CONFIRMADO, CANCELADO


    public Pagamento(Long id, BigDecimal valor, String codigo, LocalDateTime expiracao, Long pedidoId, StatusPagamento status) {
        this.id = id;
        this.valor = valor;
        this.codigo = codigo;
        this.expiracao = expiracao;
        this.pedidoId = pedidoId;
        this.status = status;
    }

    public Pagamento() {
    }

    public static PagamentoBuilder builder() {
        return new PagamentoBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public LocalDateTime getExpiracao() {
        return this.expiracao;
    }

    public Long getPedidoId() {
        return this.pedidoId;
    }

    public StatusPagamento getStatus() {
        return this.status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setExpiracao(LocalDateTime expiracao) {
        this.expiracao = expiracao;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public static class PagamentoBuilder {
        private Long id;
        private BigDecimal valor;
        private String codigo;
        private LocalDateTime expiracao;
        private Long pedidoId;
        private StatusPagamento status;

        PagamentoBuilder() {
        }

        public PagamentoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PagamentoBuilder valor(BigDecimal valor) {
            this.valor = valor;
            return this;
        }

        public PagamentoBuilder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public PagamentoBuilder expiracao(LocalDateTime expiracao) {
            this.expiracao = expiracao;
            return this;
        }

        public PagamentoBuilder pedidoId(Long pedidoId) {
            this.pedidoId = pedidoId;
            return this;
        }

        public PagamentoBuilder status(StatusPagamento status) {
            this.status = status;
            return this;
        }

        public Pagamento build() {
            return new Pagamento(this.id, this.valor, this.codigo, this.expiracao, this.pedidoId, this.status);
        }

        public String toString() {
            return "Pagamento.PagamentoBuilder(id=" + this.id + ", valor=" + this.valor + ", codigo=" + this.codigo + ", expiracao=" + this.expiracao + ", pedidoId=" + this.pedidoId + ", status=" + this.status + ")";
        }
    }
}





