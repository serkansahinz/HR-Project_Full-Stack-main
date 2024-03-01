package com.hrproject.rabbitmq.producer;

import lombok.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCompanyProducer {

  private final RabbitTemplate rabbitTemplate;

  private String exchange = "company-exchange";

  private String bindingKey = "company-delete-binding-key";



  public void deleteCompany(Long id) {
	rabbitTemplate.convertAndSend(exchange,bindingKey,id);
  }


}
