package com.hrproject.rabbitmq.producer;

import com.hrproject.rabbitmq.model.EmployeeMailModel;
import com.hrproject.rabbitmq.model.RegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendEmployeeMailProducer {

  private final RabbitTemplate rabbitTemplate;

  private String exchange = "auth-exchange";

  private String sendEmployeeMailBinding = "send-employee-mail-binding";

  public void sendNewEmployeeMail(EmployeeMailModel model) {
	rabbitTemplate.convertAndSend(exchange,sendEmployeeMailBinding,model);
  }
}
