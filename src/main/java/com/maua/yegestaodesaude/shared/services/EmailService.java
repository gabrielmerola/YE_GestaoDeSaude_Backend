package com.maua.yegestaodesaude.shared.services;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import io.github.cdimascio.dotenv.Dotenv;

public class EmailService {

    Dotenv dotenv = Dotenv.configure().load();

    public void sendForgotPasswordMail(String email, String code) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mailgun.org");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = dotenv.get("MAIL_USER");
                String password = dotenv.get("MAIL_PASSWORD");
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(dotenv.get("MAIL_USER")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Recuperação de senha!");

        String htmlForgotPasswordMail = EmailTemplates.forgotPasswordMailHtml(code);
        message.setContent(htmlForgotPasswordMail, "text/html");

        Transport.send(message);
    }
}
