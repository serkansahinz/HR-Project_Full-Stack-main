package com.hrproject.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCompanyUserProducer {

  private final RabbitTemplate rabbitTemplate;
  private String exchange = "company-exchange";
  private String bindingKeyUser = "company-user-delete-binding-key";

  public void deleteCompanyUser(Long id) {
	rabbitTemplate.convertAndSend(exchange,bindingKeyUser,id);
  }
}
