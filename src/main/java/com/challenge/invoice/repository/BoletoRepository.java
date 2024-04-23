package com.challenge.invoice.repository;

import com.challenge.invoice.model.Boleto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoRepository extends CrudRepository<Boleto, Long> {

    Iterable<Boleto> findAllByClienteId(String clienteId);
}
