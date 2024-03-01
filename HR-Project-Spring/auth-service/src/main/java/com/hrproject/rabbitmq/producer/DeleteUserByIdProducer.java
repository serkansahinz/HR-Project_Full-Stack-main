package com.hrproject.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserByIdProducer {

  private final RabbitTemplate rabbitTemplate;

  private String exchange = "auth-exchange";

  private String returnEmployeeIdBingingKey = "delete-user-id-key";

  public void deleteUserById(Long id) {
	rabbitTemplate.convertAndSend(exchange,returnEmployeeIdBingingKey,id);
  }
}
