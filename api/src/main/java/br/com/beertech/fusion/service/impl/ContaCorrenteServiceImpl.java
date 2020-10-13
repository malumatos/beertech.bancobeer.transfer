package br.com.beertech.fusion.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.beertech.fusion.domain.ContaCorrente;
import br.com.beertech.fusion.domain.Operacao;
import br.com.beertech.fusion.domain.OperationType;
import br.com.beertech.fusion.repository.ContaCorrenteRepository;
import br.com.beertech.fusion.service.ContaCorrenteService;
import br.com.beertech.fusion.service.OperationService;

@Service
public class ContaCorrenteServiceImpl implements ContaCorrenteService {

    private Logger logger = LoggerFactory.getLogger(ContaCorrenteService.class);

    private final ContaCorrenteRepository contaCorrenteRepository;

    private final OperationService operationService;

    @Autowired
    public ContaCorrenteServiceImpl(ContaCorrenteRepository contaCorrenteRepository,
            OperationService operationService) {
        this.contaCorrenteRepository = contaCorrenteRepository;
        this.operationService = operationService;
    }

    @Override
    public ContaCorrente novaContaCorrente() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String uuid = UUID.randomUUID().toString();
            String hash = DatatypeConverter.printHexBinary(md.digest(uuid.getBytes()));
            ContaCorrente novaContaCorrente = new ContaCorrente();
            novaContaCorrente.setIdentificador(hash);
            return contaCorrenteRepository.save(novaContaCorrente);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Erro ao criar conta corrente", e);
            throw new RuntimeException("Erro ao criar conta corrente", e);
        }
    }

    @Override
    public List<ContaCorrente> listContasCorrentes() {
        return contaCorrenteRepository.findAll();
    }

    @Override
    public Optional<ContaCorrente> findByIdentificador(String identificador) {
        return contaCorrenteRepository.findByIdentificador(identificador);
    }

    @Override
    public List<Operacao> listOperacoesByContaCorrente(String identificadorContaCorrente) {
        return contaCorrenteRepository.listOperacoesByContaCorrente(identificadorContaCorrente);
    }

    @Override
    @Transactional
    public void realizarTransferencia(ContaCorrente origem, ContaCorrente destino, Double valor) {
        Operacao operacaoDebito = new Operacao();
        operacaoDebito.setContaCorrente(origem);
        operacaoDebito.setTipoOperacao(OperationType.SAQUE.ID);
        operacaoDebito.setValorOperacao(valor);
        operationService.NovaTransacao(operacaoDebito);
        Operacao operacaoCredito = new Operacao();
        operacaoCredito.setContaCorrente(destino);
        operacaoCredito.setTipoOperacao(OperationType.DEPOSITO.ID);
        operacaoCredito.setValorOperacao(valor);
        operationService.NovaTransacao(operacaoCredito);
    }

}
