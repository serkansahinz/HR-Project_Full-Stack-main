package com.hrproject.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyActivationProducer {

  private final RabbitTemplate rabbitTemplate;

  private String exchange = "company-exchange";

  private String bindingKey = "company-activation-binding-key";

  public void activateStatus(String token) {
	rabbitTemplate.convertAndSend(exchange,bindingKey,token);
  }
}
