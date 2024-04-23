package com.challenge.invoice.service;

import com.challenge.invoice.model.Boleto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BoletoService {

    Iterable<Boleto> getAllbyClienteId(String clienteId);
    Optional<Boleto> getById(Long id);
    void payBoleto(Long id, Double valorPago);
    void deleteBoleto(Long id);

}
