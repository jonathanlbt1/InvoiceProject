package com.challenge.invoice.domain;

import java.util.Date;

public class Boleto {

    private Long id;
    private Long clienteId;
    private Double valor;
    private Double valorPago;
    private Date dataDeVencimento;
    private Date dataDePagamento;

}
