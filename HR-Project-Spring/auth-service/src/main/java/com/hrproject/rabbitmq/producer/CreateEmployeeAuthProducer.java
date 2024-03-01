package com.hrproject.rabbitmq.producer;

import com.hrproject.rabbitmq.model.CreateEmployeeAuthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmployeeAuthProducer {

  private String exchange = "auth-exchange";
  private String createEmployeeAuthBindingKey = "create-employee-auth-binding-key";

  private final RabbitTemplate rabbitTemplate;

  public void sendCreatedEmployeeToAuth(CreateEmployeeAuthModel createEmployeeAuthModel) {
	rabbitTemplate.convertAndSend(exchange,createEmployeeAuthBindingKey,createEmployeeAuthModel);
  }
}
