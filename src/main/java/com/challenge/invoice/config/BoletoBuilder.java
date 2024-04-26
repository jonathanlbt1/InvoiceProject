package com.challenge.invoice.config;

import com.challenge.invoice.entity.Boleto;
import com.challenge.invoice.entity.Status;
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
        boleto1.setStatus(Status.PAGO);
        boletos.add(boleto1);

        var boleto2 = new Boleto();
        boleto2.setClienteId("4308.027.846-84");
        boleto2.setValor(125.00);
        boleto2.setValorPago(127.00);
        boleto2.setDataVencimento(Date.valueOf("2024-03-10"));
        boleto2.setDataPagamento(Date.valueOf("2024-03-15"));
        boleto2.setStatus(Status.PAGO);
        boletos.add(boleto2);

        var boleto3 = new Boleto();
        boleto3.setClienteId("4308.027.846-84");
        boleto3.setValor(150.50);
        boleto3.setValorPago(150.50);
        boleto3.setDataVencimento(Date.valueOf("2024-02-15"));
        boleto3.setDataPagamento(Date.valueOf("2024-02-15"));
        boleto3.setStatus(Status.PAGO);
        boletos.add(boleto3);

        var boleto4 = new Boleto();
        boleto4.setClienteId("974.485.302-47");
        boleto4.setValor(225.00);
        boleto4.setValorPago(0.00);
        boleto4.setDataVencimento(Date.valueOf("2024-04-25"));
        boleto4.setStatus(Status.PENDENTE);
        boletos.add(boleto4);

        var boleto5 = new Boleto();
        boleto5.setClienteId("702.146.910-81");
        boleto5.setValor(75.30);
        boleto5.setValorPago(0.00);
        boleto5.setDataVencimento(Date.valueOf("2024-04-20"));
        boleto5.setStatus(Status.VENCIDO);
        boletos.add(boleto5);

        var boleto6 = new Boleto();
        boleto6.setClienteId("518.739.261-20");
        boleto6.setValor(625.00);
        boleto6.setValorPago(0.00);
        boleto6.setDataVencimento(Date.valueOf("2024-04-20"));
        boleto6.setStatus(Status.VENCIDO);
        boletos.add(boleto6);


        var boleto7 = new Boleto();
        boleto7.setClienteId("702.146.910-81");
        boleto7.setValor(175.20);
        boleto7.setValorPago(0.00);
        boleto7.setDataVencimento(Date.valueOf("2024-04-20"));
        boleto7.setStatus(Status.VENCIDO);
        boletos.add(boleto7);

        var boleto8 = new Boleto();
        boleto8.setClienteId("702.146.910-81");
        boleto8.setValor(299.30);
        boleto8.setValorPago(0.00);
        boleto8.setDataVencimento(Date.valueOf("2024-04-20"));
        boleto8.setStatus(Status.VENCIDO);
        boletos.add(boleto8);

        var boleto9 = new Boleto();
        boleto9.setClienteId("103.624.587-33");
        boleto9.setValor(499.58);
        boleto9.setValorPago(0.00);
        boleto9.setDataVencimento(Date.valueOf("2024-04-20"));
        boleto9.setStatus(Status.VENCIDO);
        boletos.add(boleto9);

        var boleto10 = new Boleto();
        boleto10.setClienteId("841.927.605-15");
        boleto10.setValor(699.58);
        boleto10.setValorPago(0.00);
        boleto10.setDataVencimento(Date.valueOf("2024-04-20"));
        boleto10.setStatus(Status.VENCIDO);
        boletos.add(boleto10);

        var boleto11 = new Boleto();
        boleto11.setClienteId("216.537.894-60");
        boleto11.setValor(199.58);
        boleto11.setValorPago(0.00);
        boleto11.setDataVencimento(Date.valueOf("2024-04-20"));
        boleto11.setStatus(Status.VENCIDO);
        boletos.add(boleto11);

        var boleto12 = new Boleto();
        boleto12.setClienteId("375.682.019-07");
        boleto12.setValor(799.58);
        boleto12.setValorPago(0.00);
        boleto12.setDataVencimento(Date.valueOf("2024-04-20"));
        boleto12.setStatus(Status.VENCIDO);
        boletos.add(boleto12);

        boletos.forEach(boletoService::createBoleto);
    }
}
