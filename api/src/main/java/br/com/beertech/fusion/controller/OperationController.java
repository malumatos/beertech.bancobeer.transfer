package br.com.beertech.fusion.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.beertech.fusion.controller.dto.OperacaoDto;
import br.com.beertech.fusion.domain.ContaCorrente;
import br.com.beertech.fusion.domain.Operacao;
import br.com.beertech.fusion.domain.Saldo;
import br.com.beertech.fusion.service.ContaCorrenteService;
import br.com.beertech.fusion.service.OperationService;
import br.com.beertech.fusion.service.SaldoService;

@RestController
@RequestMapping("/bankbeer")
public class OperationController {

    private Logger logger = LoggerFactory.getLogger(OperationController.class);

    @Autowired
    private OperationService operationService;

    @Autowired
    private SaldoService saldoService;

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    @GetMapping("/transacoes")
    public List<Operacao> listOperations() {
        return operationService.ListaTransacoes();
    }

    @GetMapping("/saldo")
    public ResponseEntity<Saldo> listSaldo() {
        try
        {
            List<Operacao> transacoes = operationService.ListaTransacoes();
            Saldo Saldo = saldoService.calcularSaldo(
                    transacoes.stream().map(Operacao::toOperacaoDto).collect(Collectors.toList()));
            return new ResponseEntity<>(Saldo, OK);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @PostMapping("/operacao")
    public ResponseEntity<Operacao> saveOperations(@RequestBody OperacaoDto operacaoDto) {
        Operacao operacao = new Operacao(operacaoDto);
        return new ResponseEntity<>(operationService.NovaTransacao(operacao), CREATED);
    }

    @GetMapping("/contascorrentes")
    public List<ContaCorrente> listContasCorrentes() {
        return contaCorrenteService.listContasCorrentes();
    }

    @PostMapping("/contascorrentes")
    public ResponseEntity<ContaCorrente> saveContaCorrente() {
        ContaCorrente novaContaCorrente = new ContaCorrente();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String uuid = UUID.randomUUID().toString();
            String hash = DatatypeConverter.printHexBinary(md.digest(uuid.getBytes()));
            novaContaCorrente.setIdentificador(hash);
            ContaCorrente contaCorrentePersisted = contaCorrenteService.novaContaCorrente(novaContaCorrente);
            return new ResponseEntity<>(contaCorrentePersisted, CREATED);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Erro ao criar conta corrente", e);
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

}
