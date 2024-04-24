package com.challenge.invoice.model;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Date;

@Entity
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cliente_id")
    private String clienteId;
    @Column(name = "valor")
    private Double valor;
    @Column(name = "valor_pago")
    private Double valorPago;
    @Column(name = "data_vencimento")
    private Date dataVencimento;
    @Column(name = "data_pagamento")
    private Date dataPagamento;
    @Column(name = "status")
    private String status;

    public Boleto(Long id, String clienteId, Double valor, Double valorPago, Date dataVencimento, Date dataPagamento,
                  String status) {
        this.id = id;
        this.clienteId = clienteId;
        this.valor = valor;
        this.valorPago = valorPago;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.status = status;
    }

    public Boleto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataDeVencimento) {
        this.dataVencimento = dataDeVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
