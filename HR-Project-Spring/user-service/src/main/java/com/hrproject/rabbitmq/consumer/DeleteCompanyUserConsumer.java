package com.hrproject.rabbitmq.consumer;

import com.hrproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteCompanyUserConsumer {

  private final UserService userService;

  @RabbitListener(queues = ("company-user-delete-queue"))
  public void deleteCompany(Long id) {
	System.out.println("SA KUYRUK ");
	log.info(" id=> {}",id);

	userService.deleteCompanyManagerById(id);
  }
}
