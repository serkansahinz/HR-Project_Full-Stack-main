package com.hrproject.rabbitmq.consumer;

import com.hrproject.rabbitmq.model.CompanyRegisterModel;
import com.hrproject.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyRegisterConsumer {

  private final CompanyService companyService;

  @RabbitListener(queues = ("company-register-queue"))
  public void newCompany(CompanyRegisterModel model) {
	companyService.createNewCompany(model);
  }
}
