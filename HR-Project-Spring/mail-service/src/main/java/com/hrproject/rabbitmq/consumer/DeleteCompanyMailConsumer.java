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
public class DeleteCompanyMailConsumer {

  private final MailService mailService;

  @RabbitListener(queues = "company-delete-mail-queue")
  public void deleteCompanyMail(String mail) {
	try {
	  mailService.sendDeleteCompanyMail(mail);
	} catch (MessagingException e) {
	  e.printStackTrace();
	}
  }
}
