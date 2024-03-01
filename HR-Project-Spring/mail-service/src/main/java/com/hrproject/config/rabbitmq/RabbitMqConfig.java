package com.hrproject.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  //Auth register
  private String mailRegisterQueue = "mail-register-queue";

  @Bean
  Queue mailRegisterQueue() {
	return new Queue(mailRegisterQueue);
  }

  //company activation queue
  private String companyActivationMailQueue = "company-activation-mail-queue";

  @Bean
  Queue companyActivationMailQueue() {
	return new Queue(companyActivationMailQueue);
  }

  private String sendEmployeeMailQueue = "send-employee-mail-queue";

  @Bean
  Queue sendEmployeeMailQueue() {
	return new Queue(sendEmployeeMailQueue);
  }

  private String deleteCompanyMailQueue = "company-delete-mail-queue";

  @Bean
  Queue deleteCompanyMailQueue() {
	return new Queue(deleteCompanyMailQueue);
  }
}