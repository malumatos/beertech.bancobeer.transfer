package br.com.beertech.fusion.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import br.com.beertech.fusion.controller.dto.TransferenciaDto;
import br.com.beertech.fusion.domain.ContaCorrente;
import br.com.beertech.fusion.domain.Operacao;

public interface ContaCorrenteService {

    ContaCorrente novaContaCorrente();

    List<ContaCorrente> listContasCorrentes();

    Optional<ContaCorrente> findByIdentificador(String identificador);

    List<Operacao> listOperacoesByContaCorrente(String identificador);

    CompletableFuture<TransferenciaDto> realizarTransferencia(ContaCorrente origem, ContaCorrente destino,
            Double valor);
}
