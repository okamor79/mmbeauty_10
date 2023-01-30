package com.okamor.mmbeauty.service;

import com.okamor.mmbeauty.model.Email;
import javax.mail.MessagingException;

public interface EmailService {

    void sendSimpleMail(Email details);

    String sendMailWhithAttachment(Email details);

    void sendMailHTMLFormat(Email email);
}
