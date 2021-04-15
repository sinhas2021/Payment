package com.secretesc.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
    private JavaMailSender sendEmailComponent;
	
	@Override
	@Async
	public void sendEmail(Integer fromAccountNo, Integer toAccountNo) {
		String fromEmail = accountService.findEmailByAccountId(fromAccountNo);
		String toEmail = accountService.findEmailByAccountId(toAccountNo);
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setFrom(fromEmail);
        msg.setSubject("Payment successful");
        msg.setText("Your transaction is successful, pls check your account balance");
        try {
			sendEmailComponent.send(msg);
		} catch (MailException e) {
			LOGGER.error("Error while sending email",e);//We should use spring retry framework to retry delivery of email 
		}
	}

}
