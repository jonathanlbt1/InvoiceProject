package com.challenge.invoice.service;

import com.challenge.invoice.entity.Boleto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;


public interface BoletoService {

    @RequestMapping(method = RequestMethod.POST, value = "/boleto")
    String createBoleto(Boleto boleto);

    @RequestMapping(method = RequestMethod.GET, value = "/boleto/cliente/{clienteId}")
    List<Boleto> getAllbyClienteId(String clienteId);

    @RequestMapping(method = RequestMethod.GET, value = "/boleto/{id}")
    Optional<Boleto> getById(Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/boleto/{id}")
    String payBoleto(Long id, Double valorPago, String dataPagamento);

    @RequestMapping(method = RequestMethod.DELETE, value = "/boleto/{id}")
    String deleteBoleto(Long id);

}
