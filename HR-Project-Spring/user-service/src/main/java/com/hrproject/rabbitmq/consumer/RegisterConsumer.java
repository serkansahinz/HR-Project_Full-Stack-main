package com.hrproject.rabbitmq.consumer;

import com.hrproject.rabbitmq.model.RegisterModel;
import com.hrproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterConsumer {

  private final UserService userService;

  @RabbitListener(queues = ("register-queue"))
  public void newUser(RegisterModel model) {
	userService.createNewUserWithRabbitmq(model);
  }
}
