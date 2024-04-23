package com.challenge.invoice.controller;

import com.challenge.invoice.model.Boleto;
import com.challenge.invoice.service.BoletoService;
import com.challenge.invoice.service.BoletoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boleto")
public class BoletoController {


    private final BoletoServiceImpl boletoService;

    public BoletoController(BoletoServiceImpl boletoService) {
        this.boletoService = boletoService;
    }

    @GetMapping()
    public ResponseEntity<Optional<List<Boleto>>> getAllBoletosByClienteId(@RequestParam String clienteId) {
        return ResponseEntity.ok(boletoService.getAllbyClienteId(clienteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleto> getBoletoById(@PathVariable Long id) {
        return ResponseEntity.ok(boletoService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<Void> payBoleto(@PathVariable Long id, @RequestParam Double valorPago) {
        boletoService.payBoleto(id, valorPago);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoleto(@PathVariable Long id) {
        boletoService.deleteBoleto(id);
        return ResponseEntity.ok().build();
    }
}
