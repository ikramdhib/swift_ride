/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.utils;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static pidev.utils.CreatePdf.createPDF;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.transform.dom.DOMSource;
/**
 *
 * @author skann
 */
public class SendQrCodeViaEmail {
    private static String verificationCode;
    private static String email;
     public static void sendQrCode(String filename,String email,BufferedImage bufferedImage) throws AddressException, MessagingException {
        try {
            //  InternetAddress[] toAddresse = { new InternetAddress(tfemail.getText())};
            // generate verification code
            
            // send verification code via email
            
            String to = email;
            String subject = "Votre Qrcode";
            String message = "Votre Qr code est dans le pdf";
            
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
               DataSource source = new FileDataSource(createPDF(filename,bufferedImage));
                msg.setDataHandler(new DataHandler(source));
                    msg.setFileName(createPDF(filename,bufferedImage).getName());
            Transport.send(msg);
        } catch (Exception ex) {
                   System.out.println(ex.getMessage());         
        }

    }


}
