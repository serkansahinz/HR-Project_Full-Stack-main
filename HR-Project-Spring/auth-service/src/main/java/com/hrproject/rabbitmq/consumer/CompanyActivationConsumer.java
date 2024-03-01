package com.hrproject.rabbitmq.consumer;

import com.hrproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyActivationConsumer {

  private final AuthService authService;

  @RabbitListener(queues = ("${rabbitmq.company-activation-queue}"))
  public void activateStatus(String token) {
	log.info("token=> {}",token);
	authService.activateStatus(token);
  }
}
