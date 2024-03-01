package com.hrproject.rabbitmq.consumer;

import com.hrproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteCompanyConsumer {

  private final AuthService authService;

  @RabbitListener(queues = ("company-delete-queue"))
  public void deleteCompany(Long id) {
	log.info("id=> {}",id);

	authService.deleteCompanyManagerById(id);
  }
}
