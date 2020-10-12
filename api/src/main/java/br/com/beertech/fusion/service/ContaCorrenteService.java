package br.com.beertech.fusion.service;

import java.util.List;

import br.com.beertech.fusion.domain.ContaCorrente;

public interface ContaCorrenteService {

    ContaCorrente novaContaCorrente(ContaCorrente contaCorrente);

    List<ContaCorrente> listContasCorrentes();

}
