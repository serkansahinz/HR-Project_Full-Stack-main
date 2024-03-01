package com.hrproject.rabbitmq.consumer;

import com.hrproject.rabbitmq.model.MailRegisterModel;
import com.hrproject.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyMailActivationConsumer {

  private final MailService mailService;

  @RabbitListener(queues = "company-activation-mail-queue")
  public void sendActivationMailToCompanyManager(String email) {
	try {
	  mailService.sendAdminConfirmationMailToManager(email);
	} catch (MessagingException e) {
	  e.printStackTrace();
	}
  }
}
