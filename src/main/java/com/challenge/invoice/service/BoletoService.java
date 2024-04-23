package com.challenge.invoice.service;

import com.challenge.invoice.model.Boleto;

import java.util.List;
import java.util.Optional;


public interface BoletoService {

    Boleto save(Boleto boleto);
    Optional<List<Boleto>> getAllbyClienteId(String clienteId);
    Optional<Boleto> getById(Long id);
    void payBoleto(Long id, Double valorPago);
    void deleteBoleto(Long id);

}
