package com.hrproject.rabbitmq.consumer;

import com.hrproject.rabbitmq.model.RegisterModel;
import com.hrproject.rabbitmq.model.SendCompanyIdToManagerModel;
import com.hrproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendCompanyIdToManagerConsumer {

  private final UserService userService;

  @RabbitListener(queues = ("company-id-manager-queue"))

  public void sendCompanyIdToManager(SendCompanyIdToManagerModel model) {
	System.out.println("consumer geldim");
	userService.sendCompanyIdToManager(model);
	System.out.println("consumerdan çıktım");
  }
}
