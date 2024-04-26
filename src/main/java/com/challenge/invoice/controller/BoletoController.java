package com.challenge.invoice.controller;

import com.challenge.invoice.entity.Boleto;
import com.challenge.invoice.service.impl.BoletoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/boleto")
public class BoletoController {

    private final BoletoServiceImpl boletoService;

    @Autowired
    public BoletoController(BoletoServiceImpl boletoService) {
        this.boletoService = boletoService;
    }


    @Operation(summary = "Retorna uma lista de boletos por CPF do cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna uma lista de boletos"),
        @ApiResponse(responseCode = "400", description = "Id do cliente não pode ser nulo")
    })
    @GetMapping()
    public ResponseEntity<List<Boleto>> getAllBoletosByClienteId(@RequestParam String clienteId) {
        if(Objects.nonNull(clienteId)) {
            return ResponseEntity.ok(boletoService.getAllbyClienteId(clienteId));
        } else {
            throw new RuntimeException("O CPF do cliente não pode ser nulo");
        }
    }


    @Operation(summary = "Retorna um boleto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna um boleto"),
            @ApiResponse(responseCode = "404", description = "Boleto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Id do cliente não pode ser nulo")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Boleto>> getBoletoById(@PathVariable Long id) {
        if(Objects.nonNull(id)) {
            return ResponseEntity.ok(boletoService.getById(id));
        } else {
            throw new RuntimeException("O id do boleto não pode ser nulo");
        }
    }


    @Operation(summary = "Faz pagamento de um boleto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boleto pago com sucesso"),
            @ApiResponse(responseCode = "404", description = "Boleto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Os campos não podem ser nulo")
    })
    @PostMapping("/{id}/pay")
    public ResponseEntity<String> payBoleto(@RequestParam Long id, Double valorPago, String dataPagamento) {
        if(Objects.nonNull(id) && Objects.nonNull(valorPago) && Objects.nonNull(dataPagamento)){
            boletoService.payBoleto(id, valorPago, dataPagamento);
            return ResponseEntity.ok().build();
        } else {
            throw new RuntimeException("Os campos indicados não podem ser nulos!");
        }
    }


    @Operation(summary = "Cria um novo boleto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boleto criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Boleto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Os campos não podem ser nulo")
    })
    @PostMapping()
    public ResponseEntity<String> createBoleto(@RequestBody Boleto boleto) {
        if(Objects.nonNull(boleto)){
            return ResponseEntity.ok(boletoService.createBoleto(boleto));
        } else {
            throw new RuntimeException("Os dados do boleto não podem ser nulos!");
        }
    }


    @Operation(summary = "Deleta um boleto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boleto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Boleto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Os campos não podem ser nulo")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoleto(@PathVariable Long id) {
        if(Objects.nonNull(id)){
            boletoService.deleteBoleto(id);
            return ResponseEntity.ok().build();
        } else {
            throw new RuntimeException("O id do boleto não pode ser nulo!");
        }
    }
}
