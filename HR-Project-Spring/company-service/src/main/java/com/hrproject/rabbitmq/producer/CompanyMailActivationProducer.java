package com.hrproject.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyMailActivationProducer {

  private final RabbitTemplate rabbitTemplate;

  private String exchange = "company-exchange";

  private String bindingKey = "company-activation-mail-binding-key";

  public void companyActivationMail(String email) {
	rabbitTemplate.convertAndSend(exchange,bindingKey,email);
  }
}
