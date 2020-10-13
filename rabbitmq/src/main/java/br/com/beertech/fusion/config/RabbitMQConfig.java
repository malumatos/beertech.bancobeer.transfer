package br.com.beertech.fusion.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${spring.rabbitmq.username}")
	String username;

	@Value("${spring.rabbitmq.password}")
	private String password;

    @Value("${fusion.rabbitmq.operationqueue}")
    String operationQueueName;

    @Value("${fusion.rabbitmq.transferqueue}")
    String transferQueueName;

    @Bean
    Queue operationQueue() {
        return new Queue(operationQueueName, false);
    }

    @Bean
    Queue transferQueue() {
        return new Queue(transferQueueName, false);
    }
}
