package br.com.beertech.fusion;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class ConsumerRabbitMQApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerRabbitMQApplication.class, args);
	}

}
