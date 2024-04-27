package com.challenge.invoice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.challenge.invoice.entity.Boleto;
import com.challenge.invoice.entity.Status;
import com.challenge.invoice.repository.BoletoRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BoletoServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BoletoServiceImplTest {

    @MockBean
    private BoletoRepository boletoRepository;

    @Autowired
    private BoletoServiceImpl boletoServiceImpl;

    private static ArrayList<Boleto> getBoletos() {
        ArrayList<Boleto> boletoList = new ArrayList<>();
        var boleto = new Boleto(
                "746.389.046-53",
                100.00,
                100.00,
                Date.from(Instant.from(Instant.now())),
                Date.from(Instant.from(Instant.now())),
                Status.PAGO
        );
        boletoList.add(boleto);

        var boleto2 = new Boleto(
                "746.389.046-53",
                100.00,
                100.00,
                Date.from(Instant.from(Instant.now())),
                Date.from(Instant.from(Instant.now())),
                Status.PAGO
        );
        boletoList.add(boleto2);
        return boletoList;
    }


    @Test
    void testThatCreateBoletoReturnsSuccessMessage() {

        var boleto = new Boleto();
        boleto.setClienteId("305.349.124-94");
        boleto.setDataPagamento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto.setDataVencimento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto.setStatus(Status.PAGO);
        boleto.setValor(10.0d);
        boleto.setValorPago(10.0d);
        when(boletoRepository.save(Mockito.any())).thenReturn(boleto);

        var boleto2 = new Boleto();
        boleto2.setClienteId("305.349.124-94");
        boleto2.setDataPagamento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto2.setDataVencimento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto2.setStatus(Status.PAGO);
        boleto2.setValor(10.0d);
        boleto2.setValorPago(10.0d);

        var actualCreateBoletoResult = boletoServiceImpl.createBoleto(boleto2);

        verify(boletoRepository).save(isA(Boleto.class));
        assertEquals("Boleto salvo com sucesso", actualCreateBoletoResult);
    }


    @Test
    void testThatCreateBoletoThrowsRuntimeException() {

        when(boletoRepository.save(Mockito.any())).thenThrow(new RuntimeException("Erro ao salvar o boleto"));

        var boleto = new Boleto();
        boleto.setClienteId("741.281.244-00");
        boleto.setDataPagamento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto.setDataVencimento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto.setStatus(Status.PAGO);
        boleto.setValor(10.0d);
        boleto.setValorPago(10.0d);

        assertThrows(RuntimeException.class, () -> boletoServiceImpl.createBoleto(boleto));
        verify(boletoRepository).save(isA(Boleto.class));
    }


    @Test
    void testThatGetAllBoletosbyClienteIdReturnsAllBoletosForAClient() {

        when(boletoRepository.findAllByClienteId(Mockito.any())).thenReturn(getBoletos());

        assertEquals(2, boletoServiceImpl.getAllbyClienteId("746.389.046-53").size(), "Contem dois boletos");
        verify(boletoRepository).findAllByClienteId(eq("746.389.046-53"));
    }


    @Test
    void testThatGetAllBoletosbyClienteIdReturnsASingleBoleto() {

        var boleto = new Boleto();
        boleto.setClienteId("143.997.558-22");
        boleto.setDataPagamento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto.setDataVencimento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto.setStatus(Status.PAGO);
        boleto.setValor(10.0d);
        boleto.setValorPago(10.0d);

        ArrayList<Boleto> boletoList = new ArrayList<>();
        boletoList.add(boleto);
        when(boletoRepository.findAllByClienteId(Mockito.any())).thenReturn(boletoList);

        List<Boleto> actualAllbyClienteId = boletoServiceImpl.getAllbyClienteId("143.997.558-22");

        verify(boletoRepository).findAllByClienteId(eq("143.997.558-22"));
        assertEquals(1, actualAllbyClienteId.size());
        assertSame(boletoList, actualAllbyClienteId);
    }


    @Test
    void testThatGetAllBoletosbyClienteIdThrowsExceptionIfNoBoletoIsFound() {

        when(boletoRepository.findAllByClienteId(Mockito.any()))
                .thenThrow(new RuntimeException("Não há boletos emitidos para este cliente"));

        assertThrows(RuntimeException.class, () -> boletoServiceImpl.getAllbyClienteId("875.956.285-47"));
        verify(boletoRepository).findAllByClienteId(eq("875.956.285-47"));
    }


    @Test
    void testThatGetBoletoByIdReturnsBoleto() {

        var boleto = new Boleto();
        boleto.setClienteId("104.103.168-81");
        boleto.setDataPagamento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto.setDataVencimento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto.setStatus(Status.PAGO);
        boleto.setValor(10.0d);
        boleto.setValorPago(10.0d);
        Optional<Boleto> ofResult = Optional.of(boleto);
        when(boletoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Optional<Boleto> actualById = boletoServiceImpl.getById(1L);

        verify(boletoRepository).findById(eq(1L));
        assertSame(ofResult, actualById);
    }


    @Test
    void testThatGetBoletoByIdThrowsExceptionIfNoBoletoIsFound() {

        Optional<Boleto> emptyResult = Optional.empty();
        when(boletoRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        assertThrows(RuntimeException.class, () -> boletoServiceImpl.getById(1L));
        verify(boletoRepository).findById(eq(1L));
    }


    @Test
    void testThatPayBoletoThrowsExceptionIfBoletoIsAlreadyPaid() {

        var boleto = new Boleto();
        boleto.setClienteId("038.584.840-40");
        boleto.setDataPagamento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto.setDataVencimento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto.setStatus(Status.PAGO);
        boleto.setValor(10.0d);
        boleto.setValorPago(10.0d);
        Optional<Boleto> ofResult = Optional.of(boleto);
        when(boletoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        assertThrows(RuntimeException.class, () -> boletoServiceImpl.payBoleto(1L,
                10.0d, "2024-04-24"));
        verify(boletoRepository).findById(eq(1L));
    }


    @Test
    void testThatPayBoletoThrowsRuntimeExceptionIfNoBoletoIsFound() {

        Optional<Boleto> emptyResult = Optional.empty();
        when(boletoRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        assertThrows(RuntimeException.class, () -> boletoServiceImpl.payBoleto(1L,
                10.0d, "2024-04-26"));
        verify(boletoRepository).findById(eq(1L));
    }


    @Test
    void testThatDeleteBoletoDeletesAGivenBoleto() {

        var boleto = new Boleto();
        boleto.setClienteId("675.939.544-12");
        boleto.setDataPagamento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto.setDataVencimento(Date.from(LocalDate.of(1970, 1, 1)
                .atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        boleto.setStatus(Status.PAGO);
        boleto.setValor(10.0d);
        boleto.setValorPago(10.0d);
        Optional<Boleto> ofResult = Optional.of(boleto);
        doNothing().when(boletoRepository).deleteById(Mockito.<Long>any());
        when(boletoRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        var actualDeleteBoletoResult = boletoServiceImpl.deleteBoleto(1L);

        verify(boletoRepository).deleteById(eq(1L));
        verify(boletoRepository).findById(eq(1L));
        assertEquals("Boleto número: 1 deletado", actualDeleteBoletoResult);
    }


    @Test
    void testThatDeleteBoletoThrowsAnExceptionIfNoBoletoIsFound() {

        Optional<Boleto> emptyResult = Optional.empty();
        when(boletoRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        assertThrows(RuntimeException.class, () -> boletoServiceImpl.deleteBoleto(1L));
        verify(boletoRepository).findById(eq(1L));
    }
}
