package com.hrproject.config.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  private String exchange = "permission-exchange";

  @Bean
  DirectExchange authExchange() {
	return new DirectExchange(exchange);
  }

}

