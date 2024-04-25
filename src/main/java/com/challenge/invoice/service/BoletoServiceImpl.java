package com.challenge.invoice.service;

import com.challenge.invoice.model.Boleto;
import com.challenge.invoice.model.Status;
import com.challenge.invoice.repository.BoletoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BoletoServiceImpl implements BoletoService {

    private final BoletoRepository boletoRepository;
    private final String BOLETO_SALVO = "Boleto salvo com sucesso";

    public BoletoServiceImpl(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    @Override
    public String createBoleto(Boleto boleto) {
        if (Objects.nonNull(boleto)){
            var newBoleto = new Boleto();
            newBoleto.setClienteId(boleto.getClienteId());
            newBoleto.setValor(boleto.getValor());
            newBoleto.setValorPago(boleto.getValorPago());
            newBoleto.setDataVencimento(boleto.getDataVencimento());
            newBoleto.setDataPagamento(boleto.getDataPagamento());
            newBoleto.setStatus(boleto.getStatus());
            boletoRepository.save(newBoleto);
            return BOLETO_SALVO;
        } else {
            throw new RuntimeException("Boleto não pode ser nulo");
        }
    }

    @Override
    public Optional<List<Boleto>> getAllbyClienteId(String clienteId) {
        if (clienteId == null) {
            throw new RuntimeException("Id do cliente não pode ser nulo");
        } else {
            return boletoRepository.findAllByClienteId(clienteId);
        }
    }

    @Override
    public Optional<Boleto> getById(Long id) {
        if (id == null) {
            throw new RuntimeException("Id não pode ser nulo");
        } else if (boletoRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Boleto não encontrado");
        } else {
            return boletoRepository.findById(id);
        }
    }

    @Override
    public void payBoleto(Long id, Double valorPago) {
        Optional<Boleto> boleto = boletoRepository.findById(id);
        if (boleto.isPresent()) {
            Boleto boleto1 = boleto.get();
            boleto1.setValorPago(valorPago);
            boleto1.setDataPagamento(Date.from(new Date().toInstant()));
            boleto1.setStatus(Status.PAGO);
            boletoRepository.save(boleto1);
        } else {
            throw new RuntimeException("Boleto não encontrado");
        }
    }

    @Override
    public void deleteBoleto(Long id) {
        if (boletoRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Boleto não encontrado");
        } else {
            boletoRepository.deleteById(id);
        }
    }
}
