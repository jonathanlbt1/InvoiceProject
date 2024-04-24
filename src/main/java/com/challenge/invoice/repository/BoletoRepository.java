package com.challenge.invoice.repository;

import com.challenge.invoice.model.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {

    Optional<List<Boleto>> findAllByClienteId(String clienteId);

}
