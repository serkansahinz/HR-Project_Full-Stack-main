package com.hrproject.rabbitmq.producer;

import com.hrproject.rabbitmq.model.CompanyRegisterModel;
import com.hrproject.rabbitmq.model.MailRegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyRegisterProducer {

  private String exchange = "auth-exchange";
  private String companyRegisterBinding = "company-register-key";

  private final RabbitTemplate rabbitTemplate;

  public void sendCompany(CompanyRegisterModel companyRegisterModel) {

	rabbitTemplate.convertAndSend(exchange,companyRegisterBinding,companyRegisterModel);
  }
}
