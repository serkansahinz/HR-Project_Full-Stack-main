package com.hrproject.service;

import com.hrproject.rabbitmq.model.EmployeeMailModel;
import com.hrproject.rabbitmq.model.MailRegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {

  private final SpringTemplateEngine templateEngine;
  private final JavaMailSender javaMailSender;

  public String sendGuestActivationMail(MailRegisterModel mailRegisterModel) throws MessagingException {

	String CONFIRMATION_URL = "http://34.30.62.124/auth/activate_status?token=" + mailRegisterModel.getToken();
	String templateName = "activation";
	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED,StandardCharsets.UTF_8.name());
	Map<String, Object> properties = new HashMap<>();
	CONFIRMATION_URL = String.format(CONFIRMATION_URL);
	properties.put("confirmationUrl",CONFIRMATION_URL);
	Context context = new Context();
	context.setVariables(properties);
	helper.setTo(mailRegisterModel.getEmail());
	helper.setSubject("Welcome to our nice platform");
	String template = templateEngine.process(templateName,context);
	helper.setText(template,true);
	javaMailSender.send(mimeMessage);
	return "Successful";
  }

  public void sendAdminConfirmationMailToManager(String mail) throws MessagingException {

	String templateName = "admin-confirm";
	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED,StandardCharsets.UTF_8.name());
	Map<String, Object> properties = new HashMap<>();
	Context context = new Context();
	context.setVariables(properties);
	helper.setTo(mail);
	helper.setSubject("Welcome to our nice platform");
	String template = templateEngine.process(templateName,context);
	helper.setText(template,true);
	javaMailSender.send(mimeMessage);
  }
//sa
  public void sendEmployeeMail(EmployeeMailModel model) throws MessagingException {
	String templateName = "employee-email";
	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED,StandardCharsets.UTF_8.name());
	Map<String, Object> properties = new HashMap<>();
	Context context = new Context();
	properties.put("email",model.getEmail());
	properties.put("password",model.getPassword());
	properties.put("name",model.getName());
	properties.put("surname",model.getSurname());
	context.setVariables(properties);
	helper.setTo(model.getPersonalEmail());
	helper.setSubject("Your company mail and password ");
	String template = templateEngine.process(templateName,context);
	helper.setText(template,true);
	javaMailSender.send(mimeMessage);
  }

  public void sendDeleteCompanyMail(String mail) throws MessagingException {
	String templateName = "admin-refuse";
	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED,StandardCharsets.UTF_8.name());
	Map<String, Object> properties = new HashMap<>();
	Context context = new Context();
	context.setVariables(properties);
	helper.setTo(mail);
	helper.setSubject("Welcome to our nice platform");
	String template = templateEngine.process(templateName,context);
	helper.setText(template,true);
	javaMailSender.send(mimeMessage);
  }
}