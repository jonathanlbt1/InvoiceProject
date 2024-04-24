package com.challenge.invoice.config;

import com.challenge.invoice.model.Boleto;
import com.challenge.invoice.model.Status;
import com.challenge.invoice.service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoletoBuilder {


    private final BoletoService boletoService;

    @Autowired
    public BoletoBuilder(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    public void runOnStartUp() {

        List<Boleto> boletos = new ArrayList<>();

        var boleto1 = new Boleto();
        boleto1.setClienteId("485.561.292-09");
        boleto1.setValor(100.00);
        boleto1.setValorPago(100.00);
        boleto1.setDataVencimento(Date.valueOf("2024-01-10"));
        boleto1.setDataPagamento(Date.valueOf("2024-01-10"));
        boleto1.setStatus(Status.PAGO.getValue());
        boletos.add(boleto1);

        var boleto2 = new Boleto();
        boleto2.setClienteId("4308.027.846-84");
        boleto2.setValor(125.00);
        boleto2.setValorPago(127.00);
        boleto2.setDataVencimento(Date.valueOf("2024-03-10"));
        boleto2.setDataPagamento(Date.valueOf("2024-03-15"));
        boleto2.setStatus(Status.PAGO.getValue());
        boletos.add(boleto2);

        var boleto3 = new Boleto();
        boleto3.setClienteId("4308.027.846-84");
        boleto3.setValor(150.50);
        boleto3.setValorPago(150.50);
        boleto3.setDataVencimento(Date.valueOf("2024-02-15"));
        boleto3.setDataPagamento(Date.valueOf("2024-02-15"));
        boleto3.setStatus(Status.PAGO.getValue());
        boletos.add(boleto3);

        var boleto4 = new Boleto();
        boleto4.setClienteId("974.485.302-47");
        boleto4.setValor(225.00);
        boleto4.setValorPago(0.00);
        boleto4.setDataVencimento(Date.valueOf("2024-04-25"));
        boleto4.setStatus(Status.PENDENTE.getValue());
        boletos.add(boleto4);

        var boleto5 = new Boleto();
        boleto5.setClienteId("702.146.910-81");
        boleto5.setValor(75.30);
        boleto5.setValorPago(0.00);
        boleto5.setDataVencimento(Date.valueOf("2024-04-20"));
        boleto5.setStatus(Status.VENCIDO.getValue());
        boletos.add(boleto5);

        var boleto6 = new Boleto();
        boleto6.setClienteId("518.739.261-20");
        boleto6.setValor(625.00);
        boleto6.setValorPago(0.00);
        boleto6.setDataVencimento(Date.valueOf("2024-04-20"));
        boleto6.setStatus(Status.VENCIDO.getValue());
        boletos.add(boleto6);

        boletos.forEach(boletoService::createBoleto);
    }
}
