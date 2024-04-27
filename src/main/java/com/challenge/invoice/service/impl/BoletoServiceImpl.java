package com.challenge.invoice.service.impl;

import com.challenge.invoice.entity.Boleto;
import com.challenge.invoice.entity.Status;
import com.challenge.invoice.repository.BoletoRepository;
import com.challenge.invoice.service.BoletoService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BoletoServiceImpl implements BoletoService {

    private final BoletoRepository boletoRepository;

    public BoletoServiceImpl(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    @Override
    public String createBoleto(Boleto boleto) {

        var newBoleto = new Boleto();
        newBoleto.setClienteId(boleto.getClienteId());
        newBoleto.setValor(boleto.getValor());
        newBoleto.setValorPago(boleto.getValorPago());
        newBoleto.setDataVencimento(boleto.getDataVencimento());
        newBoleto.setDataPagamento(boleto.getDataPagamento());
        newBoleto.setStatus(boleto.getStatus());
        boletoRepository.save(newBoleto);

        return "Boleto salvo com sucesso";
    }

    @Override
    public List<Boleto> getAllbyClienteId(String clienteId) {

        return boletoRepository.findAllByClienteId(clienteId);

    }

    @Override
    public Optional<Boleto> getById(Long id) {

        Optional<Boleto> getBoleto = boletoRepository.findById(id);
        if (getBoleto.isEmpty()) {
            throw new RuntimeException("Boleto não encontrado.");
        } else {
            return getBoleto;
        }
    }

    @Override
    public String payBoleto(Long id, Double valorPago, String dataPagamento) {

        Optional<Boleto> boleto = boletoRepository.findById(id);
        if(boleto.isPresent()) {
            if(boleto.get().getStatus().equals(Status.PAGO)) {
                throw new RuntimeException("Boleto já consta pago!");
            } else {
                var boleto1 = boleto.get();
                boleto1.setValorPago(valorPago);
                boleto1.setDataPagamento(Date.valueOf(dataPagamento));
                boleto1.setStatus(Status.PAGO);
                boletoRepository.save(boleto1);
                return "Boleto pago";
            }
        } else {
            throw new RuntimeException("Boleto não encontrado");
        }
    }

    @Override
    public String deleteBoleto(Long id) {
        if (boletoRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Boleto não encontrado");
        } else {
            boletoRepository.deleteById(id);
            return "Boleto número: " + id + " deletado";
        }
    }
}
