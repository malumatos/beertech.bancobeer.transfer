package br.com.beertech.fusion.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/saldo/{identificador}")
    public ResponseEntity<Saldo> listSaldo(@PathVariable String identificador) {
        Optional<ContaCorrente> contaCorrente = contaCorrenteService.findByIdentificador(identificador);
        if (contaCorrente.isPresent()) {
            List<Operacao> transacoes = contaCorrenteService.listOperacoesByContaCorrente(identificador);
            Saldo Saldo = saldoService
                    .calcularSaldo(transacoes.stream().map(Operacao::toOperacaoDto).collect(Collectors.toList()));
            return new ResponseEntity<>(Saldo, OK);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/operacao/{identificador}")
    public ResponseEntity<Operacao> saveOperations(@PathVariable String identificador,
            @RequestBody OperacaoDto operacaoDto) {
        Optional<ContaCorrente> contaCorrente = contaCorrenteService.findByIdentificador(identificador);
        if (contaCorrente.isPresent()) {
            Operacao operacao = new Operacao(operacaoDto);
            operacao.setContaCorrente(contaCorrente.get());
            return new ResponseEntity<>(operationService.NovaTransacao(operacao), CREATED);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/contascorrentes")
    public List<ContaCorrente> listContasCorrentes() {
        return contaCorrenteService.listContasCorrentes();
    }

    @PostMapping("/contascorrentes")
    public ResponseEntity<ContaCorrente> saveContaCorrente() {
        ContaCorrente contaCorrentePersisted = contaCorrenteService.novaContaCorrente();
        return new ResponseEntity<>(contaCorrentePersisted, CREATED);
    }

}
