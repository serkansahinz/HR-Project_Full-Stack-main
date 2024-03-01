package com.hrproject.rabbitmq.producer;

import com.hrproject.rabbitmq.model.SendCompanyIdToManagerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendCompanyIdToManagerProducer {

  private final RabbitTemplate rabbitTemplate;
  private String exchange = "company-exchange";
  private String bindingKeyManager = "company-id-manager-binding-key";

  public void sendCompanyIdToManager(SendCompanyIdToManagerModel model) {

	rabbitTemplate.convertAndSend(exchange,bindingKeyManager,model);

  }
}
