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
  private String exchange = "company-exchange";

  @Bean
  DirectExchange companyExchange() {
	return new DirectExchange(exchange);
  }

  private String companyActivationBindingKey = "company-activation-binding-key";
  private String companyActivationQueue = "company-activation-queue";

  @Bean
  Queue companyActivationQueue() {
	return new Queue(companyActivationQueue);
  }

  @Bean
  public Binding companyActivationBinding(final Queue companyActivationQueue,final DirectExchange companyExchange) {
	return BindingBuilder.bind(companyActivationQueue).to(companyExchange).with(companyActivationBindingKey);
  }

  //auth register queue

  private String companyRegisterQueue = "company-register-queue";

  @Bean
  Queue companyRegisterQueue() {
	return new Queue(companyRegisterQueue);
  }

// delete register queue auth

  private String companyDeleteQueue = "company-delete-queue";
  private String companyDeleteBinding = "company-delete-binding-key";

  @Bean
  Queue companyDeleteQueue() {
	return new Queue(companyDeleteQueue);
  }

  @Bean
  public Binding companyDeleteBinding(final Queue companyDeleteQueue,final DirectExchange companyExchange) {
	return BindingBuilder.bind(companyDeleteQueue).to(companyExchange).with(companyDeleteBinding);
  }

  // delete register queue user

  private String companyDeleteQueueUser = "company-user-delete-queue";
  private String companyDeleteBindingUser = "company-user-delete-binding-key";

  @Bean
  Queue companyDeleteQueueUser() {
	return new Queue(companyDeleteQueueUser);
  }

  @Bean
  public Binding companyDeleteBindingUser(final Queue companyDeleteQueueUser,final DirectExchange companyExchange) {
	return BindingBuilder.bind(companyDeleteQueueUser).to(companyExchange).with(companyDeleteBindingUser);
  }

  // mail activation queue companyManager
  private String companyActivationMailQueue = "company-activation-mail-queue";
  private String companyActivationMailBinding = "company-activation-mail-binding-key";

  @Bean
  Queue companyActivationMailQueue() {
	return new Queue(companyActivationMailQueue);
  }

  @Bean
  public Binding companyActivationMailBinding(final Queue companyActivationMailQueue,final DirectExchange companyExchange) {
	return BindingBuilder.bind(companyActivationMailQueue).to(companyExchange).with(companyActivationMailBinding);
  }

  private String bindingKeyManager = "company-id-manager-binding-key";
  private String companyIdManagerQueue = "company-id-manager-queue";

  @Bean
  Queue companyIdManagerQueue() {
	return new Queue(companyIdManagerQueue);
  }

  @Bean
  public Binding companyIdManagerBinding(final Queue companyIdManagerQueue,final DirectExchange companyExchange) {
	return BindingBuilder.bind(companyIdManagerQueue).to(companyExchange).with(bindingKeyManager);
  }


  private String deleteCompanyMailBinding = "company-delete-mail-binding-key";
  private String deleteCompanyMailQueue = "company-delete-mail-queue";

  @Bean
  Queue deleteCompanyMailQueue() {
	return new Queue(deleteCompanyMailQueue);
  }

  @Bean
  public Binding deleteCompanyMailBinding(final Queue deleteCompanyMailQueue,final DirectExchange companyExchange) {
	return BindingBuilder.bind(deleteCompanyMailQueue).to(companyExchange).with(deleteCompanyMailBinding);
  }
}
