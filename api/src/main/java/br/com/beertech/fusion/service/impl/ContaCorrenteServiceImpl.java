package br.com.beertech.fusion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.beertech.fusion.domain.ContaCorrente;
import br.com.beertech.fusion.repository.ContaCorrenteRepository;
import br.com.beertech.fusion.service.ContaCorrenteService;

@Service
public class ContaCorrenteServiceImpl implements ContaCorrenteService {

    private ContaCorrenteRepository contaCorrenteRepository;

    @Autowired
    public ContaCorrenteServiceImpl(ContaCorrenteRepository contaCorrenteRepository) {
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    @Override
    public ContaCorrente novaContaCorrente(ContaCorrente contaCorrente) {
        return contaCorrenteRepository.save(contaCorrente);
    }

    @Override
    public List<ContaCorrente> listContasCorrentes() {
        return contaCorrenteRepository.findAll();
    }

}
