package br.com.beertech.fusion.service;

import java.nio.charset.StandardCharsets;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.beertech.fusion.domain.Operacao;

@Service
public class OperationQueueRabbitListener {

    @RabbitListener(queues = "${fusion.rabbitmq.operationqueue}")
	public void onMessage(Message message)
	{
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = new String(message.getBody(), StandardCharsets.UTF_8);
            Operacao transaction_pojo = objectMapper.readValue(json, Operacao.class);

            if (new Validator(transaction_pojo).ValidateResponseRMQ())
                new RestClient().sendPostAPIOperation(transaction_pojo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
	}
}