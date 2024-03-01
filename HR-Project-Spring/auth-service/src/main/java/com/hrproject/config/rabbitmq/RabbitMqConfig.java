package com.hrproject.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

  private String exchange = "auth-exchange";

  @Bean
  DirectExchange authExchange() {
	return new DirectExchange(exchange);
  }
//create employee

  private String createEmployeeAuthQueue = "create-employee-auth-queue";
  private String createEmployeeAuthBindingKey = "create-employee-auth-binding-key";

  @Bean
  Queue createEmployeeAuthQueue() {
	return new Queue(createEmployeeAuthQueue);
  }

  @Bean
  public Binding createEmployeeAuthBindingKey(final Queue createEmployeeAuthQueue,final DirectExchange exchange) {
	return BindingBuilder.bind(createEmployeeAuthQueue).to(exchange).with(createEmployeeAuthBindingKey);
  }

  //user register
  private String registerQueue = "register-queue";
  private String registerBinding = "register-key";

  //  activation-binding-key: activation-key
//  activation-queue: queue-activation

  @Bean
  Queue registerQueue() {
	return new Queue(registerQueue);
  }

  @Bean
  public Binding registerBinding(final Queue registerQueue,final DirectExchange authExchange) {
	return BindingBuilder.bind(registerQueue).to(authExchange).with(registerBinding);
  }

  //Mail sender register producer
  private String mailRegisterQueue = "mail-register-queue";
  private String mailRegisterBinding = "mail-register-binding";

  @Bean
  Queue mailRegisterQueue() {
	return new Queue(mailRegisterQueue);
  }

  @Bean
  public Binding mailRegisterBinding(final Queue mailRegisterQueue,final DirectExchange authExchange) {
	return BindingBuilder.bind(mailRegisterQueue).to(authExchange).with(mailRegisterBinding);
  }

  //ACtiavtion
  private String activationQueue = "queue-activation";
  private String activationBinding = "activation-key";

  @Bean
  Queue activationQueue() {
	return new Queue(activationQueue);
  }

  @Bean
  public Binding activationBinding(final Queue activationQueue,final DirectExchange authExchange) {
	return BindingBuilder.bind(activationQueue).to(authExchange).with(activationBinding);
  }

  // Company register
  private String companyRegisterQueue = "company-register-queue";
  private String companyRegisterBinding = "company-register-key";

  @Bean
  Queue companyRegisterQueue() {
	return new Queue(companyRegisterQueue);
  }

  @Bean
  public Binding companyRegisterBinding(final Queue companyRegisterQueue,final DirectExchange authExchange) {
	return BindingBuilder.bind(companyRegisterQueue).to(authExchange).with(companyRegisterBinding);
  }

  // Company activation
  private String companyActivationQueue = "company-activation-queue";

  @Bean
  Queue companyActivationQueue() {
	return new Queue(companyActivationQueue);
  }

  //Delete company
  private String companyDeleteQueue = "company-delete-queue";

  @Bean
  Queue companyDeleteQueue() {
	return new Queue(companyDeleteQueue);
  }

  private String deleteUserById = "delete-user-id-key";
  private String deleteUserByIdQueue = "delete-user-id-queue";

  @Bean
  Queue deleteUserByIdQueue() {
	return new Queue(deleteUserByIdQueue);
  }

  @Bean
  public Binding deleteUserByIdBinding(final Queue deleteUserByIdQueue,final DirectExchange authExchange) {
	return BindingBuilder.bind(deleteUserByIdQueue).to(authExchange).with(deleteUserById);
  }


  private String sendEmployeeMailBinding = "send-employee-mail-binding";
  private String sendEmployeeMailQueue = "send-employee-mail-queue";

  @Bean
  Queue sendEmployeeMailQueue() {
	return new Queue(sendEmployeeMailQueue);
  }

  @Bean
  public Binding sendEmployeeMailBinding(final Queue sendEmployeeMailQueue,final DirectExchange authExchange) {
	return BindingBuilder.bind(sendEmployeeMailQueue).to(authExchange).with(sendEmployeeMailBinding);
  }
}

 