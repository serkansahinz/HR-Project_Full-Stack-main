package com.hrproject.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCompanyMailProducer {

  private final RabbitTemplate rabbitTemplate;

  private String exchange = "company-exchange";

  private String bindingKey = "company-delete-mail-binding-key";

  public void deleteCompanyMail(String mail) {
	rabbitTemplate.convertAndSend(exchange,bindingKey,mail);
  }
}
