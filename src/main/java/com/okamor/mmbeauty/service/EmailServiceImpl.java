package com.okamor.mmbeauty.service;

import com.okamor.mmbeauty.model.Email;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendSimpleMail(Email details) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(sender);
        msg.setTo(details.getRecipient());
        msg.setSubject(details.getSubject());
        msg.setText(details.getMsgBody());
        javaMailSender.send(msg);
    }

    @Override
    public String sendMailWhithAttachment(Email details) {
        return null;
    }

    @Override
    public void sendMailHTMLFormat(Email email) {

    }
}
