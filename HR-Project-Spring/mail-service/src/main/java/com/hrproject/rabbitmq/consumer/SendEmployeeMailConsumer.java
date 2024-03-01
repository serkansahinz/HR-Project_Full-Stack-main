package com.hrproject.rabbitmq.consumer;

import com.hrproject.rabbitmq.model.EmployeeMailModel;
import com.hrproject.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendEmployeeMailConsumer {
  private final MailService mailService;

  @RabbitListener(queues = "send-employee-mail-queue")
  public void sendNewEmployeeMail(EmployeeMailModel model) throws MessagingException {

	mailService.sendEmployeeMail(model);
  }
}
