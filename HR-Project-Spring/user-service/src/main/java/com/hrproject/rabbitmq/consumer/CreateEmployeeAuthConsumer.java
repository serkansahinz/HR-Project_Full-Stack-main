package com.hrproject.rabbitmq.consumer;

import com.hrproject.rabbitmq.model.CreateEmployeeAuthModel;
import com.hrproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateEmployeeAuthConsumer {

  private final UserService userService;

  @RabbitListener(queues = ("create-employee-auth-queue"))
  public void sendCreatedEmployeeToAuth(CreateEmployeeAuthModel model) {

	log.info("model=> {}",model);

	userService.createEmployee(model);

  }
}
