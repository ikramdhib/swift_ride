/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author skann
 */
public class ForgetPassword {
    private static String verificationCode;
    private static String email;
     public static void sendVerificationCode() throws AddressException, MessagingException {
        //  InternetAddress[] toAddresse = { new InternetAddress(tfemail.getText())};
        // generate verification code
        verificationCode = String.format("%04d", (int) (Math.random() * 10000));

        // send verification code via email
        String to = email;
        String subject = "Code de vérification pour le mot de passe oublié";
        String message = "Votre code de vérification est: " + verificationCode;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String from = "swiftride2023@gmail.com";
        String password = "xvoijqcvlwqguzpt";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
       // msg.setContent(mltprt);
        msg.setText(message);
        Transport.send(msg);

    }

    public static String  getVerificationCode() {
        return verificationCode;
    }


    public static String getEmail() {
        return email;
    }

    public static void setEmail(String a) {
        email = a;
    }
}
