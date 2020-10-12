package br.com.beertech.fusion.service;

import java.util.List;

import br.com.beertech.fusion.domain.Operacao;

public interface OperationService {

    Operacao NovaTransacao(Operacao operacao);

    void RemoveTransacao(Long idBeer);

    List<Operacao> ListaTransacoes();

}
