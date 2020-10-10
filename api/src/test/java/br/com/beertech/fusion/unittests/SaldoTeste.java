package br.com.beertech.fusion.unittests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.beertech.fusion.controller.dto.OperacaoDto;
import br.com.beertech.fusion.domain.OperationType;
import br.com.beertech.fusion.domain.Saldo;
import br.com.beertech.fusion.service.SaldoService;
import br.com.beertech.fusion.service.impl.SaldoServiceImpl;

public class SaldoTeste {

    @Test
    void testaSaldoDeposito() {
        List<OperacaoDto> operacoes = new ArrayList<>();
        operacoes.add(new OperacaoDto(OperationType.DEPOSITO, 100.));
        SaldoService saldoService = new SaldoServiceImpl();
        assertThat(saldoService.calcularSaldo(operacoes)).isEqualTo(new Saldo(100.));
    }
    
    @Test
    void testaSaldoSaque() {
        List<OperacaoDto> operacoes = new ArrayList<>();
        operacoes.add(new OperacaoDto(OperationType.SAQUE, 10.));
        SaldoService saldoService = new SaldoServiceImpl();
        assertThat(saldoService.calcularSaldo(operacoes)).isEqualTo(new Saldo(-10.));
    }
    
    @Test
    void testaSaldoOperacoesVariadas() {
        List<OperacaoDto> operacoes = new ArrayList<>();
        operacoes.add(new OperacaoDto(OperationType.DEPOSITO, 100.));
        operacoes.add(new OperacaoDto(OperationType.SAQUE, 25.));
        operacoes.add(new OperacaoDto(OperationType.SAQUE, 10.));
        SaldoService saldoService = new SaldoServiceImpl();
        assertThat(saldoService.calcularSaldo(operacoes)).isEqualTo(new Saldo(65.));
    }
    
    @Test
    void testaSaldoSemOperacoes() {
        List<OperacaoDto> operacoes = new ArrayList<>();
        SaldoService saldoService = new SaldoServiceImpl();
        assertThat(saldoService.calcularSaldo(operacoes)).isEqualTo(new Saldo(0.));
    }
}
