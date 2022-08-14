/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abroad.scholarship.config.mail;


import com.abroad.scholarship.dto.EmailSenderDto;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class SendMail implements LmsSender {

    @Override
    public void send(EmailSenderDto dto) {

        String to = dto.getTo();
        String from = "Leave Management System Portal <noreply@northwestpetroleum-ng.com>";
        String host = "smtp.office365.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.enable", "false");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("noreply@northwestpetroleum-ng.com", "Northwest123");
            }

        });
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(dto.getSubject());

            message.setContent(
              "<h3>"+dto.getContent()+"</h3>",
             "text/html");
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
