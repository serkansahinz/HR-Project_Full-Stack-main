package com.hrproject.rabbitmq.producer;

import com.hrproject.rabbitmq.model.MailRegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailRegisterProducer {

    private String exchange = "auth-exchange";
    private String mailRegisterBinding = "mail-register-binding";

    private final RabbitTemplate rabbitTemplate;
    public void sendActivationCode(MailRegisterModel registerMailModel) {

        rabbitTemplate.convertAndSend(exchange,mailRegisterBinding, registerMailModel);
    }

}
