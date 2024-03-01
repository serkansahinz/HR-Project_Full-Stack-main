package com.hrproject.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  private String exchange = "user-exchange";

  @Bean
  DirectExchange authExchange() {
	return new DirectExchange(exchange);
  }

  //auth register queue
  @Value("${rabbitmq.register-queue}")
  private String registerQueueName;
  @Value("${rabbitmq.activation-queue}")
  private String activationQueueName;

  @Bean
  Queue registerQueue() {
	return new Queue(registerQueueName);
  }

  @Bean
  public Queue activationQueue() {
	return new Queue(activationQueueName);
  }

  //Delete company
  private String companyUserDeleteQueue = "company-user-delete-queue";

  @Bean
  Queue companyDeleteQueue2() {
	return new Queue(companyUserDeleteQueue);
  }

  private String createEmployeeAuthQueue = "create-employee-auth-queue";

  @Bean
  Queue createEmployeeAuthQueue() {
	return new Queue(createEmployeeAuthQueue);
  }

  private String deleteUserByIdQueue = "delete-user-id-queue";

  @Bean
  Queue deleteUserByIdQueue() {
	return new Queue(deleteUserByIdQueue);
  }

  private String companyIdManagerQueue = "company-id-manager-queue";

  @Bean
  Queue companyIdManagerQueue() {
    return new Queue(companyIdManagerQueue);
  }
}

