package com.hrproject.controller;

import com.hrproject.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hrproject.constant.EndPoints.MAIL;

@RestController
@RequestMapping(MAIL)
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;


}
