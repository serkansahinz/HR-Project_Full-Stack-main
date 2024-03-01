package com.hrproject.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  //auth register queue
  private String exchange = "payment-exchange";

  //binding key and queue salary
  @Bean
  DirectExchange paymentExchange() {
	return new DirectExchange(exchange);
  }

//  private String salaryQueue = "salary-queue";
//  private String salaryBindingKey = "salary-binding-key";
//
//  @Bean
//  Queue salaryQueue() {
//	return new Queue(salaryQueue);
//  }
//
//  @Bean
//  public Binding salaryBindingKey(final Queue salaryQueue,final DirectExchange exchange) {
//	return BindingBuilder.bind(salaryQueue).to(exchange).with(salaryBindingKey);
//  }
}
