package com.hrproject.rabbitmq.consumer;

import com.hrproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteUserByIdConsumer {

  private final UserService userService;

  @RabbitListener(queues = ("delete-user-id-queue"))
  public void deleteUserById(Long id) {
	userService.deleteByAuthId(id);
  }
}
