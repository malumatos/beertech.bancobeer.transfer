package br.com.beertech.fusion.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.beertech.fusion.domain.Operacao;
import br.com.beertech.fusion.domain.Transferencia;

public class RestClient {

    public final String APIURL = "http://localhost:8081/bankbeer/";

    public void sendPostAPIOperation(Operacao operacao) {
        String url = APIURL + "operacao/{identificador}";
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplateBuilder().build();

        Map<String, Object> map = new HashMap<>();
        map.put("tipoOperacao", operacao.getTipoOperacao());
        map.put("valorOperacao", operacao.getValorOperacao());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<Operacao> response = restTemplate.exchange(url, HttpMethod.POST, entity, Operacao.class,
                operacao.getIdentificadorContaCorrente());

        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println(response.getBody().toString());
        } else {
            System.out.println("Sem retorno");
        }
    }

    public void sendPostAPITransferencia(Transferencia transferencia) {
        String url = APIURL + "transferencia";
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplateBuilder().build();

        Map<String, Object> map = new HashMap<>();
        map.put("identificadorContaOrigem", transferencia.getIdentificadorContaOrigem());
        map.put("identificadorContaDestino", transferencia.getIdentificadorContaDestino());
        map.put("valorTransferido", transferencia.getValorTransferido());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<Transferencia> response = restTemplate.postForEntity(url, entity, Transferencia.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println(response.getBody().toString());
        } else {
            System.out.println("Sem retorno");
        }
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
