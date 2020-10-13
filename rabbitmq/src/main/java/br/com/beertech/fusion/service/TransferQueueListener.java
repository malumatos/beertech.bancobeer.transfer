package br.com.beertech.fusion.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.beertech.fusion.domain.Transferencia;

@Service
public class TransferQueueListener {

    @RabbitListener(queues = "${fusion.rabbitmq.transferqueue}")
    public void consumerTransferQueue(@Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Transferencia transferencia = objectMapper.readValue(message, Transferencia.class);
            new RestClient().sendPostAPITransferencia(transferencia);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
